package sg.lib.rewardz_redemption.retrofit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Manish on 10/19/2015.
 *
 * Class that is triggered once the
 * Retrofit finishes making call.
 *
 */
public class MyCallback<T> implements Callback<T> {
    private RestCallback restCallback;
    private ProgressHUD mProgressHUD;
    private Context baseActivity;
    private GlobalVariables.SERVICE_MODE mode;
    private String loadingMessage;

    public MyCallback(Context context, RestCallback restCallback, boolean showProgress, String loadingMessage, GlobalVariables.SERVICE_MODE mode) {
        this.restCallback = restCallback;
        this.baseActivity = context;
        this.mode = mode;
        this.loadingMessage = loadingMessage;
        if (showProgress) {
            StartProgress();
        }
    }


    public void StartProgress() {
        if(baseActivity != null && baseActivity instanceof Activity)
            if(!((Activity)baseActivity).isFinishing())
                mProgressHUD = ProgressHUD.show(baseActivity, loadingMessage, true, false, null);
    }

    public  void StopProgress()
    {
        if(baseActivity != null && baseActivity instanceof Activity) {
            if (!((Activity) baseActivity).isFinishing()) {
                if (mProgressHUD != null)
                    try {
                        if (mProgressHUD.isShowing()) {
                            mProgressHUD.dismiss();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }else{
            if (mProgressHUD != null)
                try {
                    if (mProgressHUD.isShowing()) {
                        mProgressHUD.dismiss();
                    }

                } catch (Exception e) {

                }
        }

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        StopProgress();
        if (response.code() == 400) {
            try {
                String data = response.errorBody().string().trim();
                Object object = new JSONTokener(data).nextValue();
                if(object instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) object;
                    if(jsonObject.keys().next().equals("point_reward")) {
                        Toast.makeText(baseActivity, jsonObject.getJSONArray("point_reward").get(0).toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    JSONArray jsonArray = new JSONArray(response.errorBody().string().trim());
                    String errorMsg = jsonArray.getString(0);
                    System.out.println("Error Message : " + errorMsg);
                    Toast.makeText(baseActivity, errorMsg, Toast.LENGTH_SHORT).show();
                }
                Log.v("Error code 400",response.errorBody().string().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (restCallback != null && response.isSuccessful())
            restCallback.onSuccess(response, mode);
        else {
            System.out.println("rest callback was null");
            //Toast.makeText(baseActivity, "Something went wrong.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        StopProgress();
        if (restCallback != null)
            restCallback.onFailure(call, t , mode);
    }
}