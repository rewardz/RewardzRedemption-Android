package sg.lib.rewardz_redemption.wrapper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import sg.lib.rewardz_redemption.retrofit.GlobalVariables;
import sg.lib.rewardz_redemption.retrofit.MyCallback;
import sg.lib.rewardz_redemption.retrofit.RestCallback;
import sg.lib.rewardz_redemption.retrofit.RestService;

/**
 * Created by manishandroid on 28/06/17.
 */

public class RewardzRedemption implements RestCallback
{
    private static Context mContext;
    private static RewardzRedemption rewardzRedemption;
    private static OnAuthTokenListener onAuthTokenListener;
    public interface OnAuthTokenListener{
        void onAuthTokenReceive(String jsonResponse);
    }
    private static OnRewardsListListener onRewardsListListener;
    public interface OnRewardsListListener{
        void onRewardsListComplete(String jsonResponse);
    }
    private static OnRewardsRedeemed onRewardsRedeemed;
    public interface OnRewardsRedeemed{
        void onRewardsRedeemed(String jsonResponse);
    }


    private RewardzRedemption()
    {

    }

    public static RewardzRedemption getInstance(Context context, OnRewardsListListener onRewardsListListener, OnRewardsRedeemed onRewardsRedeemed)
    {
        if(rewardzRedemption == null)
            rewardzRedemption = new RewardzRedemption();
        //RewardzRedemption.onAuthTokenListener = onAuthTokenListener;
        RewardzRedemption.onRewardsListListener = onRewardsListListener;
        RewardzRedemption.onRewardsRedeemed = onRewardsRedeemed;
        RewardzRedemption.mContext = context;
        return rewardzRedemption;
    }

    public void getAuthToken(HashMap<String, String> mapCredentials)
    {
        RestService.getInstance(mContext).getAuthToken(mapCredentials, new MyCallback<String>(mContext,
                this, true, "Getting token...", GlobalVariables.SERVICE_MODE.AUTH_TOKEN_RECEIVE));
    }

    public void getRewardsList(String oAuthToken)
    {
        String url = GlobalVariables.BASE_URL + GlobalVariables.REWARDS_API_URL;
        RestService.getInstance(mContext).getRewards(oAuthToken, url , new MyCallback<String>(mContext,
                this, true, "Loading Rewards...", GlobalVariables.SERVICE_MODE.REWARDS));
    }

    public void redeemRewards(String oAuthToken, HashMap<String, String> map)
    {
        RestService.getInstance(mContext).redeemReward(oAuthToken, map, new MyCallback<ResponseBody>(mContext,
                this, true, "Redeeming...", GlobalVariables.SERVICE_MODE.REDEEM_REWARD));
    }

    @Override
    public void onFailure(Call call, Throwable t, GlobalVariables.SERVICE_MODE mode) {
        switch (mode)
        {
            case REWARDS:
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                break;
            case REDEEM_REWARD:
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onSuccess(Response response, GlobalVariables.SERVICE_MODE mode) {
        switch (mode)
        {
            case REWARDS:
                onRewardsListListener.onRewardsListComplete(response.body().toString());
                break;
            case REDEEM_REWARD:
                onRewardsRedeemed.onRewardsRedeemed(response.body().toString());
                break;
            case AUTH_TOKEN_RECEIVE:
                onAuthTokenListener.onAuthTokenReceive(response.body().toString());
                break;
        }
    }



}
