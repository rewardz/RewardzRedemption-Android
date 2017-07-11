package sg.lib.rewardz_redemption.retrofit;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by manishandroid on 06/05/16.
 *
 * interface that includes all the methods
 * which are used for making Retrofit API calls.
 *
 */
public interface RestInterface {


    @GET
    Call<String> getRewards(@Header("Authorization") String token, @Url String nextOrPrevUrl);

    @GET
    Call<String> getAllRewards(@Header("Authorization") String token, @Url String nextOrPrevUrl);

    @FormUrlEncoded
    @POST(GlobalVariables.REDEEM_REWARD_API_URL)
    Call<ResponseBody> redeemReward(@Header("Authorization") String token, @FieldMap Map<String, String> map);

}