[![](https://jitpack.io/v/rewardz/RewardzRedemption-Android.svg)](https://jitpack.io/#rewardz/RewardzRedemption-Android)


# RewardzRedemption-Android
This is the framework to list different type of rewards and provide functionality to redeem those rewards.

Framework is made by Rewardz developer team.

# Requirements
Rewardz Redemption Library can be included in any Android application.

Rewardz Redemption Library supports Android 2.3 (Gingerbread) and later.

# Using Rewardz Redemption Library in your application

## Step 1. 
Add the JitPack repository to your build file

`allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}`

## Step 2.
Add this in your build.gradle

`compile 'com.github.rewardz:RewardzRedemption-Android:0.1.3'`

## Step 3.
Sync gradle.

## Step 4.

In your Activity/Fragment class implements `RewardzRedemption.OnRewardsListListener, RewardzRedemption.OnRewardsRedeemed`
and override methods `onRewardsListComplete()` and `onRewardsRedeemed()`.

Your class should look like below:

    public class MainActivity extends AppCompatActivity implements RewardzRedemption.OnRewardsListListener, RewardzRedemption.OnRewardsRedeemed
    {
    
      @Override
      public void onRewardsListComplete(String jsonResponse) {
          Log.v("Tag", "onRewardsListComplete ==> "+jsonResponse);
      }

      @Override
      public void onRewardsRedeemed(String jsonResponse) {
          Log.v("Tag", "onRewardsRedeemed ==> "+jsonResponse);
      }
    }
    
## Step 5.
Collect oAuth Token provided by our support team or from admin panel and put it in your source code.

     private final String oAuthToken = "Token <token_by_admin_panel>";

## Step 6.
To Load rewards list in your app, you have to call:

     RewardzRedemption.getInstance(this, this, this).getRewardsList(oAuthToken);
     
This line will give you all rewards in JSON array format that you will get in below overrided method:

      @Override
            public void onRewardsListComplete(String jsonResponse) {
                Log.v("Tag", "onRewardsListComplete ==> "+jsonResponse);
            }
     
To Redeem perticular reward in your app, you have to call:

     RewardzRedemption.getInstance(this, this, this, this).redeemRewards(oAuthToken, paramMap);
     
This line will give you result in JSON format after redeeming any reward that you will get in below overrided method:

     @Override
          public void onRewardsRedeemed(String jsonResponse) {
              Log.v("Tag", "onRewardsRedeemed ==> "+jsonResponse);
          }
     
And, It's done. Voilla...!

Start creating your own UI as per your app design and load rewards list and give your app's user to redeem them whenever they want. Happy Coding.

## Find this project useful ? ❤️

- Support it by clicking the ⭐️ button on the upper right of this page. ✌️

## Contact - Let's become friends

- [Rewardz Contact](https://rewardz.sg/rewardz/#contact)

- [Rewardz Github](https://github.com/rewardz)

- [Rewardz Facebook](https://www.facebook.com/RewardzSG/)

- [Rewardz Twitter](https://twitter.com/REWARDZ_PTE_LTD)

- [Rewardz LinkedIn](https://www.linkedin.com/company-beta/2709071/)

## License

    Copyright (C) 2016 Rewardz Pte Ltd
    Copyright (C) 2011 Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## Contributing to RewardzRedemption Framework

Just make pull request. You're in!
