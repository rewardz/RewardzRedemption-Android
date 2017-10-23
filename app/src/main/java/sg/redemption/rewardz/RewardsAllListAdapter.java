package sg.redemption.rewardz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import sg.lib.rewardz_redemption.retrofit.GlobalVariables;

/**
 * Created by manishandroid on 13/05/16.
 * <p>
 * Adapter class that loads the RecyclerView with
 * all rewards.
 */
public class RewardsAllListAdapter extends RecyclerView.Adapter<RewardsAllListAdapter.ViewHolder> {

    static Context mContext;
    List<AllRewardsModel.Result> mRewardsModelList = new ArrayList<AllRewardsModel.Result>();

    public RewardsAllListAdapter(Context context, List<AllRewardsModel.Result> models) {
        mContext = context;
        this.mRewardsModelList = models;
    }

    public static void openRewardsDetail(AllRewardsModel.Result model) {
        //Intent intent = new Intent(mContext, RewardsDetailActivity.class);
        //intent.putExtra("jsonobject", new Gson().toJson(model));
        //mContext.startActivity(intent);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_rewardz, null);
        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllRewardsModel.Result modelResult = mRewardsModelList.get(position);
        //System.out.println("JSON FROM BVH 1 => "+new Gson().toJson(modelResult));
        holder.tvRewards.setText(modelResult.getName());
        holder.tvRewardsDesc.setText(modelResult.getDescription());
        holder.tvRewardsPoint.setText(modelResult.getCost());
        Glide.with(mContext).load(GlobalVariables.BASE_URL + modelResult.getImg()).into(holder.ivRewards);
        if (modelResult.getIsRedeemable())
            holder.ivRedeemRewards.setVisibility(View.VISIBLE);
        else
            holder.ivRedeemRewards.setVisibility(View.GONE);

        if (modelResult.getIsFeatured())
            holder.ivFeatured.setVisibility(View.VISIBLE);
        else
            holder.ivFeatured.setVisibility(View.GONE);
        //System.out.println("JSON FROM BVH 2 => "+new Gson().toJson(modelResult));
        holder.model = modelResult;
    }

    @Override
    public int getItemCount() {
        return mRewardsModelList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRewards, tvRewardsDesc, tvRewardsPoint;
        private ImageView ivRedeemRewards, ivRewards, ivFeatured;
        private AllRewardsModel.Result model;

        public ViewHolder(final Context context, @NonNull final View itemView) {
            super(itemView);
            tvRewards = (TextView) itemView.findViewById(R.id.reward);
            tvRewardsDesc = (TextView) itemView.findViewById(R.id.rewards_desc);
            tvRewardsPoint = (TextView) itemView.findViewById(R.id.rewardzpts);
            ivRedeemRewards = (ImageView) itemView.findViewById(R.id.redeemrewardz);
            ivRewards = (ImageView) itemView.findViewById(R.id.rewardz_img);
            ivFeatured = (ImageView) itemView.findViewById(R.id.iv_star_featured);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //System.out.println("JSON FROM ONCLICK => "+new Gson().toJson(model));
                    openRewardsDetail(model);
                }
            });
        }

    }

}
