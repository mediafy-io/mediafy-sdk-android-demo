

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
import com.mediafy.sdk.api.adunits.MediafyAdView;
import com.mediafy.sdk.api.adunits.MediafyAdViewListener;
import com.mediafy.sdk.api.adunits.utils.MediafyAdException;
import com.mediafy.sdk.api.adunits.utils.MediafyAdSize;

public class MediafyBanner300x250Activity extends BaseAdActivity {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;
    private static final String AD_UNIT_ID = "ad_unit_id";

    private MediafyAdView adView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adView != null) {
            adView.destroy();
        }
    }


    private void createAd() {
        // 1. Create ad view
        adView = new MediafyAdView(this);
        adView.setAdUnitId(AD_UNIT_ID);

        // 2. Configure ad unit
        adView.setAdSizes(new MediafyAdSize(WIDTH, HEIGHT));
        adView.setAutoRefreshDelay(30);
        adView.setBannerListener(createListener());

        // 3. Load ad
        adView.loadAd();

        // 4. Add MediafyAdView to the app UI
        getContainerForAd().addView(adView);
    }

    private static MediafyAdViewListener createListener() {
        return new MediafyAdViewListener() {
            @Override
            public void onAdLoaded(MediafyAdView bannerView) {
                // Called when ad loaded
                Log.d(TAG, "Ad loaded successfully");
            }

            @Override
            public void onAdFailed(MediafyAdView bannerView, MediafyAdException e) {
                // Called when ad failed to load
                Log.e(TAG, "Ad failed to load: " + e.getMessage(), e);
            }

            @Override
            public void onAdDisplayed(MediafyAdView bannerView) {
                // Called when ad displayed on screen
            }

            @Override
            public void onAdClicked(MediafyAdView bannerView) {
                // Called when ad clicked
            }

            @Override
            public void onAdClosed(MediafyAdView bannerView) {
                // Called when ad hidden
            }
        };
    }

}
