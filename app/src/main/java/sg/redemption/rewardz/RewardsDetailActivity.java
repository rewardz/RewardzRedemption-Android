/*
package sg.redemption.rewardz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.root.MyApplication;
import com.root.flab.Constants.Constants;
import com.root.flab.HttpRequest.CheckInternetConnection;
import com.root.flab.checkin.MapActivity;
import com.root.flab.checkin.MultipleAddressActivity;
import com.root.flabulous.Activities.DashboardActivity;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.RedeemRewardModel;
import retrofit.GlobalVariables;
import retrofit.MyCallback;
import retrofit.RestCallback;
import retrofit.RestService;
import retrofit2.Call;
import retrofit2.Response;

*/
/*
    Activity that shows the details of Reward.
	Makes API call to Redeem the Rewards.
 *//*

public class RewardsDetailActivity extends Activity implements OnClickListener,
        LocationListener, RestCallback {
    public static int locationSetsid = 0;
    TextView title, title_text, phone_number, website, emailText;
    TextView address;
    String jsonString;
    ImageView background_image;
    ImageView mapPushPin;
    Double lattitude;
    String pk;
    Double longitude;
    LinearLayout checkinView, dashBoardView;
    JSONArray location_setJsonArray;
    @Nullable
    AlertDialog alertDialog = null;
    @NonNull
    ArrayList<String> addressArray = new ArrayList<String>();
    CheckInternetConnection checkInternetConnection;
    ImageView facilityWeblink;
    LinearLayout back;
    WebView termsandConditions;
    ImageView downArrow;
    LocationManager locationManager;
    String provider;
    Location location;
    Double rewardLatitude = 0d;
    Double rewardLongitude = 0d;
    TextView valid;
    TextView redeemThisReward;
    boolean isLocationReward = false;
    ExpandableTextView descriptionRewardz, expandableTextViewAboutUs;
    String type, brochureUrl;
    LinearLayout dialNumber;
    LinearLayout emailicon;
    boolean isPasswordbased = false;
    TextView tvMoreDetails, tvBrochure;
    String points, costOfRewards;

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        locationManager.requestLocationUpdates(provider, 400, 1, this);
        try {
            if (location_setJsonArray != null) {
                for (int i = 0; i < location_setJsonArray.length(); i++) {
                    isLocationReward = true;
                    JSONObject locationFirstJsonObject = location_setJsonArray.getJSONObject(locationSetsid);
                    address.setText(locationFirstJsonObject.getString("address1") + "  " + locationFirstJsonObject.getString("address2"));
                    rewardLatitude = Double.parseDouble(locationFirstJsonObject.getString("lat"));
                    rewardLongitude = Double.parseDouble(locationFirstJsonObject.getString("lng"));
                    phone_number.setText(locationFirstJsonObject.getString("phone"));
                    pk = locationFirstJsonObject.getString("pk");
                    isPasswordbased = locationFirstJsonObject.getBoolean("has_flash_password");
                    //rewardsPoints=locationFirstJsonObject.getString("point_reward");
                    //website.setText(locationFirstJsonObject.getString("website"));
                    emailText.setText(locationFirstJsonObject.getString("email"));
                    //JSONObject locationJsonObject=location_setJsonArray.getJSONObject(i);
                    //addressArray.add(locationJsonObject.getString("address1"));

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewardz_detail);


        String selectedBaseURL = getApplicationContext().getSharedPreferences("token", 1).getString(Constants.KEY_SELECTED_SERVICE, "");
        String selectedImgBaseURL = getApplicationContext().getSharedPreferences("token", 1).getString(Constants.KEY_SELECTED_IMAGE_SERVICE, "");
        if (selectedBaseURL != null && !selectedBaseURL.equals(""))
            ((MyApplication) getApplicationContext()).setBaseUrl(selectedBaseURL);
        if (selectedImgBaseURL != null && !selectedImgBaseURL.equals(""))
            ((MyApplication) getApplicationContext()).setImgBaseUrl(selectedImgBaseURL);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        location = locationManager.getLastKnownLocation(provider);
        if (location != null) {

            lattitude = location.getLatitude();
            longitude = location.getLongitude();
        }


        checkInternetConnection = new CheckInternetConnection(getApplicationContext());
        //bookmark=(LinearLayout)findViewById(R.id.bookmark);
        back = (LinearLayout) findViewById(R.id.back_arrow);
        termsandConditions = (WebView) findViewById(R.id.terms_conditions);

        //termsandConditions.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "appfonts/Roboto-Light.ttf"));

        back.setOnClickListener(this);
        facilityWeblink = (ImageView) findViewById(R.id.facility_weblink);
        dialNumber = (LinearLayout) findViewById(R.id.dial);

        facilityWeblink.setOnClickListener(this);
        //facilityCheckin=(LinearLayout)findViewById(R.id.facility_checkin);
        title = (TextView) findViewById(R.id.title);
        downArrow = (ImageView) findViewById(R.id.down_arrow);
        descriptionRewardz = (ExpandableTextView) findViewById(R.id.expand_text_view);
        expandableTextViewAboutUs = (ExpandableTextView) findViewById(R.id.expand_text_view_about);
        background_image = (ImageView) findViewById(R.id.background_image);
        title_text = (TextView) findViewById(R.id.title_text);
        title.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "appfonts/Roboto-Light.ttf"));
        address = (TextView) findViewById(R.id.address);
        emailicon = (LinearLayout) findViewById(R.id.email_text);
        address.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "appfonts/Roboto-Light.ttf"));
        checkinView = (LinearLayout) findViewById(R.id.checkin_view);
        dashBoardView = (LinearLayout) findViewById(R.id.dashboard_view);
        phone_number = (TextView) findViewById(R.id.phone_number);
        phone_number.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "appfonts/Roboto-Light.ttf"));
        website = (TextView) findViewById(R.id.website);
        website.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "appfonts/Roboto-Light.ttf"));
        emailText = (TextView) findViewById(R.id.emailtext);
        emailText.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "appfonts/Roboto-Light.ttf"));
        jsonString = getIntent().getStringExtra("jsonobject");
        //System.out.println("JSON STRING RESULT ==> "+jsonString);
        background_image = (ImageView) findViewById(R.id.background_image);
        mapPushPin = (ImageView) findViewById(R.id.map_pushpin);
        valid = (TextView) findViewById(R.id.valid);
        redeemThisReward = (TextView) findViewById(R.id.redeemthisreward);
        tvMoreDetails = (TextView) findViewById(R.id.tv_more_details);
        tvBrochure = (TextView) findViewById(R.id.tv_brochure);
        Linkify.addLinks(tvBrochure, Linkify.ALL);
        redeemThisReward.setOnClickListener(this);
        checkinView.setOnClickListener(this);
        dashBoardView.setOnClickListener(this);
        mapPushPin.setOnClickListener(this);
        address.setOnClickListener(this);
        dialNumber.setOnClickListener(this);
        emailicon.setOnClickListener(this);
        downArrow.setOnClickListener(this);
        updateUi();
        */
/*	if(getIntent().getStringExtra("address").equals(""))
        {
			updateUi();
		}
		else
		{
			address.setText(getIntent().getStringExtra("address"));
		}*//*

        if (addressArray.size() > 1) {
            downArrow.setVisibility(View.VISIBLE);
        }


        //getActionBar().hide();
    }


    */
/*
        Update the UI once the data is retrieved from API call.
     *//*

    public void updateUi() {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String selectedImgBaseURL = getApplicationContext().getSharedPreferences("token", 1).getString(Constants.KEY_SELECTED_IMAGE_SERVICE, "");
            String detailImage = selectedImgBaseURL + jsonObject.getString("display_img");
            Glide.with(getApplicationContext())
                    .load(detailImage)
                    .into(background_image);
            title.setText(jsonObject.getString("name").toUpperCase());
            title_text.setText(jsonObject.getString("merchant").toUpperCase());
            phone_number.setText(jsonObject.getString("contact_phone"));
            website.setText(jsonObject.getString("website"));
            points = jsonObject.getString("cost");
            costOfRewards = jsonObject.getString("cost");
            String summary = jsonObject.getString("terms_and_conditions");
            termsandConditions.loadUrl("javascript:document.body.style.color ='yellow';");
            termsandConditions.setAlpha(0.3f);
            termsandConditions.loadData(summary, "text/html", null);

            boolean isValidDateAvlbl = jsonObject.has("valid_until");       // this key was missing in some cases
            if (isValidDateAvlbl) {
                String validdate = jsonObject.optString("valid_until");
                if (validdate != null) {
                    String[] datePart = validdate.split("T");
                    String[] splitDates = datePart[0].split("-");
                    String month = splitDates[1];
                    int monthIndex = Integer.parseInt(month);
                    --monthIndex;

                    String formatedDate = splitDates[2] + " " + Constants.MONTHS[monthIndex] + " " + splitDates[0];
                    valid.setText("VALID TILL  " + formatedDate);
                } else {
                    valid.setVisibility(View.GONE);
                }

            } else {
                valid.setVisibility(View.GONE);
            }
            descriptionRewardz.setText(jsonObject.getString("description"));
            expandableTextViewAboutUs.setText(jsonObject.getString("merchant_description"));
            JSONObject redemtionJson = jsonObject.getJSONObject("redemption");
            type = redemtionJson.getString("type");
            if (jsonObject.getBoolean("is_redeemable") == false) {
                //redeemThisReward.setTextColor(Color.parseColor("#a4a4a4"));
                redeemThisReward.setVisibility(View.GONE);
                //redeemThisReward.setClickable(false);
            }

            if (!jsonObject.getString("brochure").trim().equals("")) {
                tvBrochure.setVisibility(View.GONE);
                tvMoreDetails.setVisibility(View.VISIBLE);
                brochureUrl = Constants.REWARDS_IMG_BASE_URL + jsonObject.getString("brochure");

				*/
/*If it's pdf, then chrome will automatically download it. This condition is used to stop
				* automatic download of pdf file.*//*

                if (brochureUrl.substring(brochureUrl.length() - 3, brochureUrl.length()).equalsIgnoreCase("pdf")) {
                    brochureUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + brochureUrl;
                }

                tvMoreDetails.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(brochureUrl));
                        startActivity(intent);
                    }
                });
            } else {
                tvBrochure.setVisibility(View.GONE);
                tvMoreDetails.setVisibility(View.GONE);
            }
					
			*/
/*String desc=jsonObject.getString("description");
			descriptionText.setText(desc);
			Picasso.with(getApplicationContext())
			.load(jsonObject.getString("img"))

			.into(background_image);
			JSONObject categoryJson=jsonObject.getJSONObject("category");
			title_text.setText(categoryJson.getString("name").toUpperCase());*//*

            if (jsonObject.has("locations")) {
                location_setJsonArray = jsonObject.getJSONArray("locations");
            } else {
                location_setJsonArray = jsonObject.getJSONArray("nearby_locations");
            }
            if (location_setJsonArray.length() > 1 || type.equals("flash")) {
                for (int i = 0; i < location_setJsonArray.length(); i++) {
                    isLocationReward = true;
                    JSONObject locationFirstJsonObject = location_setJsonArray.getJSONObject(0);
                    address.setText(locationFirstJsonObject.getString("address1"));
                    rewardLatitude = Double.parseDouble(locationFirstJsonObject.getString("lat"));
                    rewardLongitude = Double.parseDouble(locationFirstJsonObject.getString("lng"));
                    JSONObject locationJsonObject = location_setJsonArray.getJSONObject(i);
                    addressArray.add(locationJsonObject.getString("address1"));
                    phone_number.setText(locationFirstJsonObject.getString("phone"));
                    pk = locationFirstJsonObject.getString("pk");
                    emailText.setText(locationFirstJsonObject.getString("email"));
                    //rewardsPoints=locationFirstJsonObject.getString("point_reward");


                }
            } else {
                isLocationReward = false;
                pk = jsonObject.getString("pk");
                for (int i = 0; i < location_setJsonArray.length(); i++) {
                    isLocationReward = true;


                    JSONObject locationFirstJsonObject = location_setJsonArray.getJSONObject(0);
                    address.setText(locationFirstJsonObject.getString("address1") + "  " + locationFirstJsonObject.getString("address2"));
                    rewardLatitude = Double.parseDouble(locationFirstJsonObject.getString("lat"));
                    rewardLongitude = Double.parseDouble(locationFirstJsonObject.getString("lng"));
                    JSONObject locationJsonObject = location_setJsonArray.getJSONObject(i);
                    addressArray.add(locationJsonObject.getString("address1"));
                    phone_number.setText(locationFirstJsonObject.getString("phone"));
                    isPasswordbased = locationFirstJsonObject.getBoolean("has_flash_password");

                    emailText.setText(locationFirstJsonObject.getString("email"));
                    //rewardsPoints=locationFirstJsonObject.getString("point_reward");


                }

            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(@NonNull View view) {

        switch (view.getId()) {
            case R.id.checkin_view:
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("selectedfragment", Constants.CHECKIN_FRAGMENT);
                startActivity(intent);
                break;

            case R.id.back_arrow:
                DashboardActivity.isCalledFromResume = false;
                if (getIntent().getExtras().getBoolean("isFromPush")) {
                    Intent intentBack = new Intent(getApplicationContext(), DashboardActivity.class);
                    intentBack.putExtra("selectedfragment", Constants.DASHBOARD);
                    startActivity(intentBack);
                } else
                    finish();
                break;
            case R.id.dashboard_view:
                Intent intentDashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                intentDashboard.putExtra("selectedfragment", Constants.DASHBOARD);
                startActivity(intentDashboard);
                break;

            case R.id.facility_weblink:
                try {
                    if (website.getText().toString() != null && !website.getText().toString().equals("")) {
                        Intent webLinkIntent = new Intent(Intent.ACTION_VIEW);
                        webLinkIntent.setData(Uri.parse(website.getText().toString()));
                        startActivity(webLinkIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Website address not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.redeemthisreward:
                if (Double.parseDouble(costOfRewards) > 0.0)
                    showAlertDialog();

                else
                    redeemReward();

                break;

            case R.id.email_text:
                if (emailText.getText().toString() != null && !emailText.getText().toString().equals("")) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{emailText.getText().toString()});
                    i.putExtra(Intent.EXTRA_SUBJECT, "");
                    i.putExtra(Intent.EXTRA_TEXT, "");
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ignored) {
                        Toast.makeText(RewardsDetailActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Email address not found", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.dial:
                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone_number.getText().toString(), null));
                startActivity(intentDial);
                break;

            case R.id.down_arrow:
                if (addressArray.size() > 1) {

                    Intent intentAddress = new Intent(getApplicationContext(), MultipleAddressActivity.class);
                    intentAddress.putStringArrayListExtra("addressarray", addressArray);
                    startActivity(intentAddress);
                }
                break;

            case R.id.map_pushpin:
                if (checkInternetConnection.isConnectingToInternet()) {
                    Intent intentMap = new Intent(getApplicationContext(), MapActivity.class);
                    intentMap.putExtra("latitude", rewardLatitude);
                    intentMap.putExtra("longitude", rewardLongitude);
                    intentMap.putExtra("area", address.getText().toString());
                    startActivityForResult(intentMap, 1);
                    //startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "NO Internet Connection", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }

    */
/*
        Retrofit API call to redeem the reward.
     *//*

    public void callRewardRedeemAPI(HashMap<String, String> map) {

        SharedPreferences sharedPreferences = getSharedPreferences("token", 1);
        String token = "Token " + sharedPreferences.getString("token", "");
        RestService.getInstance(this).redeemReward(token, map, new MyCallback<RedeemRewardModel>(this, this, true, GlobalVariables.SERVICE_MODE.REDEEM_REWARD));
    }

    */
/*
      Call this method if Redeeming the reward is password based.
     *//*

    public void raisePasswordAlert() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RewardsDetailActivity.this);
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alertBuilder.setTitle("Request vendor for security passcode");
        View view = layoutInflater.inflate(R.layout.passworddialog, null);
        final EditText eventPassword = (EditText) view.findViewById(R.id.eventpassword);

        alertBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                String password = eventPassword.getText().toString();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("location", pk);
                map.put("password", password);
                callRewardRedeemAPI(map);
                //callPasswordTrueApi();
            }
        });
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }

        });

        alertBuilder.setView(view);
        alertDialog = alertBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onLocationChanged(@Nullable Location location) {
        // TODO Auto-generated method stub
        if (location != null) {

            lattitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        //  System.out.println("location is "+lat+"       "+lng);
    }

    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFailure(Call call, Throwable t, GlobalVariables.SERVICE_MODE mode) {

    }

    @Override
    public void onSuccess(@NonNull Response response, @NonNull GlobalVariables.SERVICE_MODE mode) {
        switch (mode) {
            case REDEEM_REWARD:
                RedeemRewardModel redeemRewardModel = (RedeemRewardModel) response.body();
                String jsonResponse = new Gson().toJson(redeemRewardModel);
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    if (jsonObject.has("non_field_errors")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("non_field_errors");
                        if (jsonArray.length() > 0) {
                            String error = jsonArray.optString(0);
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                        }
                    }

                    String codeGenerated = jsonObject.optString("code");
                    Intent intent = new Intent(getApplicationContext(), RedeemedRewardzActivity.class);
                    intent.putExtra("code", codeGenerated);
                    intent.putExtra("jsonobject", jsonString);
                    intent.putExtra("address", address.getText().toString());
                    intent.putExtra("latitude", rewardLatitude);
                    intent.putExtra("longitude", rewardLongitude);
                    intent.putExtra("type", type);

                    startActivity(intent);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void startActivityForResult(@Nullable Intent intent, int requestCode) {
        */
/*try {
            super.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }*//*


        if (intent == null) {
            intent = new Intent();
        }
        super.startActivityForResult(intent, requestCode);
    }

    private void showAlertDialog() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        alert.setTitle("Redeem Reward");
        alert.setMessage("This will deduct " + points + " pts from your account. Are you sure you want to redeem this reward?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                redeemReward();
                dialogInterface.dismiss();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alert.show();
    }

    private void redeemReward() {
        HashMap<String, String> map = new HashMap<>();
        if (checkInternetConnection.isConnectingToInternet()) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            if (isLocationReward) {
                map.put("location", pk);
                if (isPasswordbased) {
                    raisePasswordAlert();
                    return;
                }
            } else {
                map.put("reward", pk);
            }
            callRewardRedeemAPI(map);
        } else {
            Toast.makeText(getApplicationContext(), "NO Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

}
*/
