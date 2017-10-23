package sg.redemption.rewardz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import sg.lib.rewardz_redemption.wrapper.RewardzRedemption;

/*
* This class is used to show rewards list
* On click of any reward you will see reward detail page
* where you can redeem perticular reward.
* */
public class MainActivity extends AppCompatActivity implements RewardzRedemption.OnRewardsListListener, RewardzRedemption.OnRewardsRedeemed{

    private String oAuthToken = "Token 0e9a843aab22a8106c249b7b0b69d636a37de3e8";
    private RecyclerView rvAllRewards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        callRewardsListAPI();

    }

    private void initViews()
    {
        rvAllRewards = (RecyclerView) findViewById(R.id.rvAllRewards);
        rvAllRewards.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvAllRewards.setLayoutManager(llm);
        rvAllRewards.addItemDecoration(new DividerItemDecoration(this, null));
    }

    private void callRewardsListAPI(){
        RewardzRedemption.getInstance(this, this, this).getRewardsList(oAuthToken);
    }


    @Override
    public void onRewardsListComplete(String jsonResponse) {
        Log.v("Tag", "onRewardsListComplete ==> "+jsonResponse);
        AllRewardsModel model = new Gson().fromJson(jsonResponse, AllRewardsModel.class);
        RewardsAllListAdapter adapter = new RewardsAllListAdapter(this, model.getResults());
        rvAllRewards.setAdapter(adapter);

    }

    @Override
    public void onRewardsRedeemed(String jsonResponse) {
        Log.v("Tag", "onRewardsRedeemed ==> "+jsonResponse);
    }
}
