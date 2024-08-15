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
import com.mediafy.sdk.api.adunits.MediafyAdViewVideoListener;
import com.mediafy.sdk.api.adunits.utils.MediafyAdException;
import com.mediafy.sdk.api.adunits.utils.MediafyAdSize;
import com.mediafy.sdk.api.adunits.utils.MediafyAdUnitFormat;
import com.mediafy.sdk.api.adunits.utils.MediafyVideoPlacementType;

public class MediafyVideoBannerActivity extends BaseAdActivity {

    private static final String AD_UNIT_ID = "ad_unit_id";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;

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
        // 1. Create BannerView
        adView = new MediafyAdView(this);

        // 2. Configure ad unit
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setAdSizes(new MediafyAdSize(WIDTH, HEIGHT));
        adView.setVideoPlacementType(MediafyVideoPlacementType.IN_BANNER);
        adView.setBannerListener(createListener());
        adView.setBannerVideoListener(createVideoListener());
        adView.setAdUnitFormat(MediafyAdUnitFormat.VIDEO);

        // 3. Load ad
        adView.loadAd();

        // 4. Add BannerView to the app UI
        getContainerForAd().addView(adView);
    }


    private static MediafyAdViewListener createListener() {
        return new MediafyAdViewListener() {
            @Override
            public void onAdLoaded(MediafyAdView mediafyAdView) {
                // Called when ad loaded
                Log.d(TAG, "Ad loaded successfully");
            }

            @Override
            public void onAdFailed(MediafyAdView mediafyAdView, MediafyAdException e) {
                // Called when ad failed to load
                Log.e(TAG, "Ad failed to load: " + e.getMessage());
            }

            @Override
            public void onAdDisplayed(MediafyAdView mediafyAdView) {
                // Called when ad displayed on screen
            }

            @Override
            public void onAdClicked(MediafyAdView mediafyAdView) {
                // Called when ad clicked
            }

            @Override
            public void onAdClosed(MediafyAdView mediafyAdView) {
                // Called when ad hidden
            }
        };
    }

    private static MediafyAdViewVideoListener createVideoListener() {
        return new MediafyAdViewVideoListener() {
            @Override
            public void onVideoCompleted(MediafyAdView mediafyAdView) {
                // Called when video ad completed
                Log.d(TAG, "Video completed");
            }

            @Override
            public void onVideoPaused(MediafyAdView mediafyAdView) {
                // Called when video ad paused
            }

            @Override
            public void onVideoResumed(MediafyAdView mediafyAdView) {
                // Called when video ad resumed
            }

            @Override
            public void onVideoUnMuted(MediafyAdView mediafyAdView) {
                // Called when video ad un muted
            }

            @Override
            public void onVideoMuted(MediafyAdView mediafyAdView) {
                // Called when video ad muted
            }
        };
    }

}
