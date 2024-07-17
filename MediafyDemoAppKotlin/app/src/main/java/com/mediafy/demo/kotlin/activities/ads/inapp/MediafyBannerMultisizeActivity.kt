/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.inapp

import android.os.Bundle
import android.util.Log
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.sdk.api.adunits.MediafyAdView
import com.mediafy.sdk.api.adunits.MediafyAdViewListener
import com.mediafy.sdk.api.adunits.utils.MediafyAdException
import com.mediafy.sdk.api.adunits.utils.MediafyAdSize

class MediafyBannerMultisizeActivity : BaseAdActivity() {

    private var adView: MediafyAdView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createAd()
    }

    override fun onDestroy() {
        super.onDestroy()
        adView?.destroy()
    }


    private fun createAd() {
        // 1. Create BannerView
        val adView = MediafyAdView(this).also { this.adView = it }

        // 2. Configure ad unit
        adView.setAdUnitId(AD_UNIT_ID)
        adView.setAdSizes(MediafyAdSize(320, 50), MediafyAdSize(728, 90))
        adView.setBannerListener(createListener())
        adView.setAutoRefreshDelay(30)

        // 3. Load ad
        adView.loadAd()

        // 4. Add BannerView to the app UI
        containerForAd.addView(adView)
    }

    companion object {
        private const val AD_UNIT_ID = "ad_unit_id"

        private fun createListener(): MediafyAdViewListener {
            return object : MediafyAdViewListener {
                override fun onAdLoaded(bannerView: MediafyAdView) {
                    // Called when ad loaded
                    Log.d(TAG, "Ad loaded successfully")
                }

                override fun onAdFailed(bannerView: MediafyAdView?, e: MediafyAdException?) {
                    // Called when ad failed to load or parse
                    Log.e(TAG, "Ad failed to load: " + e?.message)
                }

                override fun onAdDisplayed(bannerView: MediafyAdView) {
                    // Called when ad displayed
                }

                override fun onAdClicked(bannerView: MediafyAdView) {
                    // Called when ad clicked
                }

                override fun onAdClosed(bannerView: MediafyAdView) {
                    // Called when ad closed
                }
            }
        }
    }

}
