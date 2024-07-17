/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.admob;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.mediafy.demo.java.activities.BaseAdActivity;
import com.mediafy.sdk.gad.MediafyGadMediationAdapter;

public class AdMobInterstitialMultiformatActivity extends BaseAdActivity {

    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-2844566227051243/3108198151";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    private void createAd() {
        // 1. Create ad request
        AdRequest adRequest = new AdRequest.Builder().build();

        // 2. Load ad
        InterstitialAd.load(this, ADMOB_AD_UNIT_ID, adRequest, createLoadingListener(this));
    }

    private static InterstitialAdLoadCallback createLoadingListener(Activity activity) {
        return new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                Log.d(TAG, "Ad loaded successfully");
                interstitialAd.setFullScreenContentCallback(createAdListener());

                // 3. Show ad
                interstitialAd.show(activity);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.e(TAG, "Ad failed to load: " + loadAdError);
            }
        };
    }


    private static FullScreenContentCallback createAdListener() {
        return new FullScreenContentCallback() {
            @Override
            public void onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent();
                Log.d(TAG, "Full screen ad displayed");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
                Log.d(TAG, "Full screen ad closed");
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                Log.e(TAG, "Failed to show ad: " + adError.getMessage());
            }
        };
    }

}
