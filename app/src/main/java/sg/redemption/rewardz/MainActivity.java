package sg.redemption.rewardz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import sg.lib.rewardz_redemption.retrofit.GlobalVariables;
import sg.lib.rewardz_redemption.wrapper.RewardzRedemption;


public class MainActivity extends AppCompatActivity implements RewardzRedemption.OnRewardsListListener, RewardzRedemption.OnRewardsRedeemed{

    private String oAuthToken = "Token 0e9a843aab22a8106c249b7b0b69d636a37de3e8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RewardzRedemption.getInstance(this, this, this).getRewardsList(oAuthToken, GlobalVariables.BASE_URL+GlobalVariables.REWARDS_API_URL);

    }

    @Override
    public void onRewardsListComplete(String jsonResponse) {
        Log.v("Tag", "onRewardsListComplete ==> "+jsonResponse);
    }

    @Override
    public void onRewardsRedeemed(String jsonResponse) {
        Log.v("Tag", "onRewardsRedeemed ==> "+jsonResponse);
    }
}
