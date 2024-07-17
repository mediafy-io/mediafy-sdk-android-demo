/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.admob

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.sdk.gad.MediafyGadMediationAdapter

class AdMobInterstitialActivity : BaseAdActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }

    private fun createAd() {
        // 1. Create ad request
        val adRequest = AdRequest.Builder().build()

        // 2. Load ad
        InterstitialAd.load(
            this, ADMOB_AD_UNIT_ID, adRequest, createLoadingListener(
                this
            )
        )
    }

    companion object {
        private const val ADMOB_AD_UNIT_ID = "ca-app-pub-2844566227051243/7889573931"

        private fun createLoadingListener(activity: Activity): InterstitialAdLoadCallback {
            return object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    Log.d(TAG, "Ad loaded successfully")
                    interstitialAd.fullScreenContentCallback = createAdListener()

                    // 3. Show ad
                    interstitialAd.show(activity)
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e(TAG, "Ad failed to load: $loadAdError")
                }
            }
        }


        private fun createAdListener(): FullScreenContentCallback {
            return object : FullScreenContentCallback() {
                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    Log.d(TAG, "Full screen ad displayed")
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    Log.d(TAG, "Full screen ad closed")
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    super.onAdFailedToShowFullScreenContent(adError)
                    Log.e(TAG, "Failed to show ad: " + adError.message)
                }
            }
        }
    }
}