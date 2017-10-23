package sg.lib.rewardz_redemption.retrofit;

/**
 * Created by manishandroid on 06/05/16.
 *
 * List of all end-point APIs'
 */
public class GlobalVariables {

    public static final String BASE_URL = "https://flabuless.rewardz.sg/v3/";
    //public static final String BASE_URL = "http://lendleaserenew.flabulessdev.com/v3/";

    public static final String LOGIN_API_URL = "api-token-auth/";
    public static final String REWARDS_API_URL = "rewards/";
    public static final String REDEEM_REWARD_API_URL="rewards/redemption/";


    public enum SERVICE_MODE {
        REWARDS,
        REDEEM_REWARD,
        AUTH_TOKEN_RECEIVE
    }
}
