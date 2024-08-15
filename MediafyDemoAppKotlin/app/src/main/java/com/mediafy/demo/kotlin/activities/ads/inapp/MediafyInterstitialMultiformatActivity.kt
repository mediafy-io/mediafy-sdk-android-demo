/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.inapp

import android.os.Bundle
import android.util.Log
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.sdk.api.adunits.MediafyInterstitialAdUnit
import com.mediafy.sdk.api.adunits.MediafyInterstitialAdUnitListener
import com.mediafy.sdk.api.adunits.utils.MediafyAdException
import com.mediafy.sdk.api.adunits.utils.MediafyAdSize
import com.mediafy.sdk.api.adunits.utils.MediafyAdUnitFormat
import java.util.EnumSet

class MediafyInterstitialMultiformatActivity : BaseAdActivity() {

    private var adUnit: MediafyInterstitialAdUnit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (adUnit != null) {
            adUnit?.destroy()
        }
    }

    private fun createAd() {
        // 1. Create InterstitialAdUnit
        adUnit = MediafyInterstitialAdUnit(this)

        // 2. Configure ad unit
        adUnit?.setAdUnitId(AD_UNIT_ID)
        adUnit?.setAdSizes(MediafyAdSize(1024, 768), MediafyAdSize(320, 480))
        adUnit?.setAdUnitFormats(EnumSet.of(MediafyAdUnitFormat.BANNER, MediafyAdUnitFormat.VIDEO))
        adUnit?.setInterstitialAdUnitListener(createListener())

        // 3. Load ad
        adUnit?.loadAd()
    }

    companion object {
        private const val AD_UNIT_ID = "ad_unit_id"

        private fun createListener(): MediafyInterstitialAdUnitListener {
            return object : MediafyInterstitialAdUnitListener {
                override fun onAdLoaded(adUnit: MediafyInterstitialAdUnit) {
                    // Called when ad loaded
                    Log.d(TAG, "Ad loaded successfully")

                    // 4. Show ad
                    adUnit.show()
                }

                override fun onAdDisplayed(adUnit: MediafyInterstitialAdUnit) {
                    // Called when ad displayed full screen
                    Log.d(TAG, "Ad displayed")
                }

                override fun onAdFailed(
                    adUnit: MediafyInterstitialAdUnit,
                    e: MediafyAdException,
                ) {
                    // Called when ad failed to load or parse
                    Log.e(TAG, "Ad failed to load: " + e.message)
                }

                override fun onAdClicked(adUnit: MediafyInterstitialAdUnit) {
                    // Called when ad clicked
                }

                override fun onAdClosed(adUnit: MediafyInterstitialAdUnit) {
                    // Called when ad closed
                }
            }
        }
    }
}