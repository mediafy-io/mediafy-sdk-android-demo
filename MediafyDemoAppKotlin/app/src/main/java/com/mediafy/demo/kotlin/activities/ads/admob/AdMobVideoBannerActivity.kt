/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.admob

import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.*
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.sdk.gad.MediafyGadMediationAdapter

class AdMobVideoBannerActivity : BaseAdActivity() {
    private var adMobAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (adMobAdView != null) {
            adMobAdView!!.destroy()
        }
    }


    private fun createAd() {
        // 1. Create ad request
        val adRequest = AdRequest.Builder().build()

        // 2. Create GMA AdView
        adMobAdView = AdView(this).apply {

            // 3. Configure ad unit
            adUnitId = ADMOB_AD_UNIT_ID
            adListener = createListener()
            setAdSize(AdSize.MEDIUM_RECTANGLE)

            // 4. Load ad
            loadAd(adRequest)
        }

        // 5. Add ad view to the app UI
        containerForAd.addView(adMobAdView)
    }

    companion object {
        private const val ADMOB_AD_UNIT_ID = "ca-app-pub-2844566227051243/5496378728"

        private fun createListener(): AdListener {
            return object : AdListener() {
                override fun onAdLoaded() {
                    Log.d(TAG, "Ad loaded successfully")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e(TAG, "Ad failed to load: $loadAdError")
                }
            }
        }
    }
}