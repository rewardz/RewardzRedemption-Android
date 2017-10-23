package sg.lib.rewardz_redemption.retrofit;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import sg.lib.rewardz_redemption.BuildConfig;

/**
 * Created by manishandroid on 06/05/16.
 *
 * Class that initialises the Retrofit.
 * List of all the methods that are used
 * for making Retrofit API calls.
 *
 *
 */
public class RestService {

    private static RestService restService;
    private static RestInterface restInterface;
    private static Context mContext;

    private RestService() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //clientBuilder.addInterceptor(new ChuckInterceptor(mContext).showNotification(true));
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
            clientBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        }

        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        System.out.println("REST SERVICE BASE URL : "+ GlobalVariables.BASE_URL);
        String baseUrl = GlobalVariables.BASE_URL;
        restInterface = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
                .create(RestInterface.class);
    }

    public static RestService getInstance(Context context) {
        if (restService == null) {
            mContext = context;
            restService = new RestService();
        }

        return restService;
    }


    public static void changeApiBaseUrl(String newApiBaseUrl) {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(new ChuckInterceptor(mContext).showNotification(true));
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping();
        System.out.println("REST SERVICE BASE URL : "+(GlobalVariables.BASE_URL));

        restInterface = new Retrofit.Builder()
                .baseUrl(newApiBaseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
                .create(RestInterface.class);
    }


    public void getAuthToken(Map<String, String> map, MyCallback<String> callback) {

        Call<String> call = restInterface.login(map);
        call.enqueue(callback);
    }

    public void getRewards(String token, String nextOrPrevUrl, MyCallback<String> callback){
        Call<String> call = restInterface.getRewards(token, nextOrPrevUrl);
        call.enqueue(callback);
    }

    public void getAllRewards(String token, String nextOrPrevUrl, MyCallback<String> callback){
        Call<String> call = restInterface.getAllRewards(token, nextOrPrevUrl);
        call.enqueue(callback);
    }

    public void redeemReward(String token, Map<String, String> map, MyCallback<ResponseBody> callback){
        Call<ResponseBody> call = restInterface.redeemReward(token, map);
        call.enqueue(callback);
    }
}

