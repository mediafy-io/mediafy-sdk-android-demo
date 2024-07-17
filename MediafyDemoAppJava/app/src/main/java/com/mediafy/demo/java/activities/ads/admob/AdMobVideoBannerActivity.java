/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.admob;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.*;
import com.mediafy.demo.java.activities.BaseAdActivity;
import com.mediafy.sdk.gad.MediafyGadMediationAdapter;

public class AdMobVideoBannerActivity extends BaseAdActivity {

    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-2844566227051243/5496378728";

    private AdView adMobAdView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adMobAdView != null) {
            adMobAdView.destroy();
        }
    }


    private void createAd() {
        // 1. Create ad request
        AdRequest adRequest = new AdRequest.Builder().build();

        // 2. Create GMA AdView
        adMobAdView = new AdView(this);

        // 3. Configure ad unit
        adMobAdView.setAdUnitId(ADMOB_AD_UNIT_ID);
        adMobAdView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adMobAdView.setAdListener(createListener());

        // 4. Load ad
        adMobAdView.loadAd(adRequest);

        // 5. Add ad view to the app UI
        getContainerForAd().addView(adMobAdView);
    }

    private static AdListener createListener() {
        return new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Ad loaded successfully");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.e(TAG, "Ad failed to load: " + loadAdError);
            }
        };
    }

}
