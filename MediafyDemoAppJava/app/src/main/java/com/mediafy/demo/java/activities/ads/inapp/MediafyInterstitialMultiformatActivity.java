/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.inapp;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.mediafy.demo.java.activities.BaseAdActivity;
import com.mediafy.sdk.api.adunits.MediafyInterstitialAdUnit;
import com.mediafy.sdk.api.adunits.MediafyInterstitialAdUnitListener;
import com.mediafy.sdk.api.adunits.utils.MediafyAdException;
import com.mediafy.sdk.api.adunits.utils.MediafyAdSize;
import com.mediafy.sdk.api.adunits.utils.MediafyAdUnitFormat;

import java.util.EnumSet;

public class MediafyInterstitialMultiformatActivity extends BaseAdActivity {

    private static final String AD_UNIT_ID = "ad_unit_id";
    private MediafyInterstitialAdUnit adUnit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adUnit != null) {
            adUnit.destroy();
        }
    }


    private void createAd() {
        // 1. Create MediafyMediafyInterstitialAdUnit
        adUnit = new MediafyInterstitialAdUnit(this);

        // 2. Configure ad unit
        adUnit.setAdUnitFormats(EnumSet.of(MediafyAdUnitFormat.BANNER, MediafyAdUnitFormat.VIDEO));
        adUnit.setAdSizes(new MediafyAdSize(1024, 768), new MediafyAdSize(320, 480));
        adUnit.setAdUnitId(AD_UNIT_ID);
        adUnit.setInterstitialAdUnitListener(createListener());

        // 3. Load ad
        adUnit.loadAd();
    }

    private static MediafyInterstitialAdUnitListener createListener() {
        return new MediafyInterstitialAdUnitListener() {
            @Override
            public void onAdLoaded(MediafyInterstitialAdUnit adUnit) {
                // Called when ad loaded
                Log.d(TAG, "Ad loaded successfully");

                // 4. Show ad
                adUnit.show();
            }

            @Override
            public void onAdDisplayed(MediafyInterstitialAdUnit adUnit) {
                // Called when ad displayed full screen
                Log.d(TAG, "Ad displayed");
            }

            @Override
            public void onAdFailed(MediafyInterstitialAdUnit adUnit, MediafyAdException e) {
                // Called when ad failed to load
                Log.e(TAG, "Ad failed to load: " + e.getMessage());
            }

            @Override
            public void onAdClicked(MediafyInterstitialAdUnit adUnit) {
                // Called when ad clicked
            }

            @Override
            public void onAdClosed(MediafyInterstitialAdUnit adUnit) {
                // Called when ad closed
            }
        };
    }

}
