/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.applovin;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;
import com.mediafy.demo.java.activities.BaseAdActivity;

public class AppLovinBanner300x250Activity extends BaseAdActivity {

    private static final String APP_LOVIN_AD_UNIT_ID = "ce00781594252d58";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;

    private MaxAdView appLovinAdView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (appLovinAdView != null) {
            appLovinAdView.destroy();
        }
    }


    private void createAd() {
        // 1. Create MaxAdView
        MaxAdFormat adFormat = MaxAdFormat.MREC; // For banner 320x50 MaxAdFormat.BANNER
        appLovinAdView = new MaxAdView(APP_LOVIN_AD_UNIT_ID, adFormat, this);

        // 2. Configure ad unit
        appLovinAdView.setListener(createListener());
        appLovinAdView.setLayoutParams(new ViewGroup.LayoutParams(
                AppLovinSdkUtils.dpToPx(this, WIDTH),
                AppLovinSdkUtils.dpToPx(this, HEIGHT)
        ));

        // 3. Load ad
        appLovinAdView.loadAd();

        // 4. Add ad view to the app UI
        getContainerForAd().addView(appLovinAdView);
    }

    private static MaxAdViewAdListener createListener() {
        return new MaxAdViewAdListener() {
            @Override
            public void onAdLoaded(@NonNull MaxAd maxAd) {
                Log.d(TAG, "Ad loaded successfully");
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
            public void onAdExpanded(@NonNull MaxAd maxAd) {
            }

            @Override
            public void onAdCollapsed(@NonNull MaxAd maxAd) {
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
