/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.applovin

import android.os.Bundle
import android.util.Log
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.mediafy.demo.kotlin.activities.BaseAdActivity

class AppLovinInterstitialMultiformatActivity : BaseAdActivity() {
    private var appLovinAdUnit: MaxInterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (appLovinAdUnit != null) {
            appLovinAdUnit!!.destroy()
        }
    }


    private fun createAd() {
        // 1. Create MaxInterstitialAd
        appLovinAdUnit = MaxInterstitialAd(APP_LOVIN_AD_UNIT_ID, this)

        // 2. Configure ad unit
        appLovinAdUnit?.setListener(createListener(appLovinAdUnit!!))

        // 3. Load ad
        appLovinAdUnit?.loadAd()
    }

    companion object {
        private const val APP_LOVIN_AD_UNIT_ID = "4d461cc2c39a39ca"

        private fun createListener(ad: MaxInterstitialAd): MaxAdListener {
            return object : MaxAdListener {
                override fun onAdLoaded(maxAd: MaxAd) {
                    Log.d(TAG, "Ad loaded successfully")

                    // 4. Show ad
                    ad.showAd()
                }

                override fun onAdLoadFailed(s: String, maxError: MaxError) {
                    Log.e(TAG, "Ad loading failed: " + maxError.message)
                }

                override fun onAdDisplayFailed(maxAd: MaxAd, maxError: MaxError) {
                    Log.e(TAG, "Ad displaying failed: " + maxError.message)
                }

                override fun onAdDisplayed(maxAd: MaxAd) {
                }

                override fun onAdHidden(maxAd: MaxAd) {
                }

                override fun onAdClicked(maxAd: MaxAd) {
                }
            }
        }
    }
}