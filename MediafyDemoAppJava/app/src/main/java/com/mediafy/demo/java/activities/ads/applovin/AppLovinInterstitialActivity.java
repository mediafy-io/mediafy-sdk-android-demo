/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.applovin;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.mediafy.demo.java.activities.BaseAdActivity;

public class AppLovinInterstitialActivity extends BaseAdActivity {

    private static final String APP_LOVIN_AD_UNIT_ID = "342d598f108afeb6";

    private MaxInterstitialAd appLovinAdUnit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (appLovinAdUnit != null) {
            appLovinAdUnit.destroy();
        }
    }


    private void createAd() {
        // 1. Create MaxInterstitialAd
        appLovinAdUnit = new MaxInterstitialAd(APP_LOVIN_AD_UNIT_ID, this);

        // 2. Configure ad unit
        appLovinAdUnit.setListener(createListener(appLovinAdUnit));

        // 3. Load ad
        appLovinAdUnit.loadAd();
    }

    private static MaxAdListener createListener(MaxInterstitialAd ad) {
        return new MaxAdListener() {
            @Override
            public void onAdLoaded(@NonNull MaxAd maxAd) {
                Log.d(TAG, "Ad loaded successfully");

                // 4. Show ad
                ad.showAd();
            }

            @Override
            public void onAdLoadFailed(@NonNull String s, @NonNull MaxError maxError) {
                Log.e(TAG, "Ad loading failed: " + maxError.getMessage());
            }

            @Override
            public void onAdDisplayFailed(@NonNull MaxAd maxAd, @NonNull MaxError maxError) {
                Log.e(TAG, "Ad displaying failed: " + maxError.getMessage());
            }

            @Override
            public void onAdDisplayed(@NonNull MaxAd maxAd) {
            }

            @Override
            public void onAdHidden(@NonNull MaxAd maxAd) {
            }

            @Override
            public void onAdClicked(@NonNull MaxAd maxAd) {
            }
        };
    }

}
