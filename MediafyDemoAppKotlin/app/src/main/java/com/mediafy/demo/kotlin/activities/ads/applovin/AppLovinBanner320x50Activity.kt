/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.applovin

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdFormat
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.applovin.sdk.AppLovinSdkUtils
import com.mediafy.demo.kotlin.activities.BaseAdActivity

class AppLovinBanner320x50Activity : BaseAdActivity() {

    private var appLovinAdView: MaxAdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (appLovinAdView != null) {
            appLovinAdView!!.destroy()
        }
    }


    private fun createAd() {
        // 1. Create MaxAdView
        val adFormat = MaxAdFormat.BANNER
        appLovinAdView = MaxAdView(APP_LOVIN_AD_UNIT_ID, adFormat, this).apply {
            // 2. Configure ad unit
            setListener(createListener())
            layoutParams = ViewGroup.LayoutParams(
                AppLovinSdkUtils.dpToPx(this@AppLovinBanner320x50Activity, WIDTH),
                AppLovinSdkUtils.dpToPx(this@AppLovinBanner320x50Activity, HEIGHT)
            )

            // 3. Load ad
            loadAd()
        }

        // 4. Add ad view to the app UI
        containerForAd.addView(appLovinAdView)
    }

    companion object {
        private const val APP_LOVIN_AD_UNIT_ID = "1915c2d5d79062b4"
        private const val WIDTH = 320
        private const val HEIGHT = 50

        private fun createListener(): MaxAdViewAdListener {
            return object : MaxAdViewAdListener {
                override fun onAdLoaded(maxAd: MaxAd) {
                    Log.d(TAG, "Ad loaded successfully")
                }

                override fun onAdLoadFailed(s: String, maxError: MaxError) {
                    Log.e(TAG, "Ad loading failed: " + maxError.message)
                }

                override fun onAdDisplayFailed(maxAd: MaxAd, maxError: MaxError) {
                    Log.e(TAG, "Ad displaying failed: " + maxError.message)
                }

                override fun onAdExpanded(maxAd: MaxAd) {
                }

                override fun onAdCollapsed(maxAd: MaxAd) {
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