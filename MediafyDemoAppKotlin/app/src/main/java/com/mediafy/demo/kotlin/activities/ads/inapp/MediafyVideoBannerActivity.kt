/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.inapp

import android.os.Bundle
import android.util.Log
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.sdk.api.adunits.MediafyAdViewVideoListener
import com.mediafy.sdk.api.adunits.MediafyAdView
import com.mediafy.sdk.api.adunits.MediafyAdViewListener
import com.mediafy.sdk.api.adunits.utils.MediafyAdException
import com.mediafy.sdk.api.adunits.utils.MediafyAdSize
import com.mediafy.sdk.api.adunits.utils.MediafyAdUnitFormat
import com.mediafy.sdk.api.adunits.utils.MediafyVideoPlacementType

class MediafyVideoBannerActivity : BaseAdActivity() {

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
        adView.setAdSizes(MediafyAdSize(WIDTH, HEIGHT))
        adView.setBannerListener(createListener())
        adView.setBannerVideoListener(createVideoListener())
        adView.setAdUnitFormat(MediafyAdUnitFormat.VIDEO)
        adView.videoPlacementType = MediafyVideoPlacementType.IN_BANNER
        adView.setAutoRefreshDelay(30)

        // 3. Load ad
        adView.loadAd()

        // 4. Add BannerView to the app UI
        containerForAd.addView(adView)
    }

    companion object {
        private const val WIDTH = 300
        private const val HEIGHT = 250
        private const val AD_UNIT_ID = "ad_unit_id"

        private fun createListener(): MediafyAdViewListener {
            return object : MediafyAdViewListener {
                override fun onAdLoaded(mediafyAdView: MediafyAdView) {
                    // Called when ad loaded
                    Log.d(TAG, "Ad loaded successfully")
                }

                override fun onAdFailed(mediafyAdView: MediafyAdView, e: MediafyAdException) {
                    // Called when ad failed to load or parse
                    Log.e(TAG, "Ad failed to load: " + e.message)
                }

                override fun onAdDisplayed(mediafyAdView: MediafyAdView) {
                    // Called when ad displayed
                }

                override fun onAdClicked(mediafyAdView: MediafyAdView) {
                    // Called when ad clicked
                }

                override fun onAdClosed(mediafyAdView: MediafyAdView) {
                    // Called when ad closed
                }
            }
        }

        private fun createVideoListener(): MediafyAdViewVideoListener {
            return object : MediafyAdViewVideoListener {
                override fun onVideoCompleted(mediafyAdView: MediafyAdView) {
                    // Called when video completed
                    Log.d(TAG, "Video completed")
                }

                override fun onVideoPaused(mediafyAdView: MediafyAdView) {
                    // Called when video paused
                }

                override fun onVideoResumed(mediafyAdView: MediafyAdView) {
                    // Called when video resumed
                }

                override fun onVideoUnMuted(mediafyAdView: MediafyAdView) {
                    // Called when video unmuted
                }

                override fun onVideoMuted(mediafyAdView: MediafyAdView) {
                    // Called when video muted
                }
            }
        }

    }

}
