/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.inapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.mediafy.demo.kotlin.R
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.demo.kotlin.utils.ImageUtils
import com.mediafy.sdk.api.adunits.MediafyNativeAdUnit
import com.mediafy.sdk.api.adunits.MediafyNativeAdUnitEventListener
import com.mediafy.sdk.api.adunits.nativead.request.*
import com.mediafy.sdk.api.adunits.nativead.response.MediafyNativeAd
import com.mediafy.sdk.api.adunits.nativead.response.MediafyNativeResult
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker.EventTrackingMethod
import java.util.*

class MediafyNativeActivity : BaseAdActivity() {

    private var adUnit: MediafyNativeAdUnit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (adUnit != null) {
            adUnit!!.destroy()
        }
    }


    private fun createAd() {
        // 1. Create NativeAdUnit
        adUnit = MediafyNativeAdUnit()

        // 2. Configure ad unit with native config
        adUnit?.setNativeAdConfig(createNativeConfig())

        // 3. Load ad
        adUnit?.loadAd { result: MediafyNativeResult ->
            val nativeAd = result.nativeAd
            if (nativeAd == null) {
                Log.e("AdExample", "Native ad is null: " + result.status)
                return@loadAd
            }

            Log.d(TAG, "Native ad loaded successfully")
            // 4. Create native view
            createNativeView(nativeAd)
        }
    }


    private fun createNativeConfig(): MediafyNativeAdConfig {
        val ctaText = NativeDataAsset(NativeDataAsset.DataType.CTATEXT)
        ctaText.len = 15

        val title = NativeTitleAsset(90)
        title.isRequired = true

        val icon = NativeImageAsset()
        icon.wMin = 50
        icon.hMin = 50
        icon.imageType = NativeImageAsset.ImageType.ICON

        val image = NativeImageAsset()
        image.w = 1200
        image.h = 627
        image.imageType = NativeImageAsset.ImageType.MAIN
        image.isRequired = true

        val rating = NativeDataAsset(NativeDataAsset.DataType.RATING)

        val description = NativeDataAsset(NativeDataAsset.DataType.DESC)
        description.isRequired = true
        description.len = 150

        val assets = listOf(ctaText, title, icon, image, rating, description)

        val methods: ArrayList<EventTrackingMethod> = ArrayList()
        methods.add(EventTrackingMethod.IMAGE)
        methods.add(EventTrackingMethod.JS)
        val tracker = NativeEventTracker(NativeEventTracker.EventType.IMPRESSION, methods)


        val config = MediafyNativeAdConfig()
        config.setContextType(NativeContextType.SOCIAL_CENTRIC)
        config.setPlacementType(NativePlacementType.CONTENT_FEED)
        config.setContextSubType(NativeContextSubtype.GENERAL_SOCIAL)
        config.setNativeEventTrackers(listOf(tracker))
        config.setNativeAssets(assets)

        return config
    }

    private fun createNativeView(ad: MediafyNativeAd) {
        val nativeContainer = View.inflate(this, R.layout.layout_native, null)

        val icon = nativeContainer.findViewById<ImageView>(R.id.imgIcon)
        ImageUtils.download(ad.iconUrl, icon)

        val title = nativeContainer.findViewById<TextView>(R.id.tvTitle)
        title.text = ad.title

        val image = nativeContainer.findViewById<ImageView>(R.id.imgImage)
        ImageUtils.download(ad.imageUrl, image)

        val description = nativeContainer.findViewById<TextView>(R.id.tvDesc)
        description.text = ad.description

        val cta = nativeContainer.findViewById<Button>(R.id.btnCta)
        cta.text = ad.callToAction

        containerForAd.addView(nativeContainer)

        ad.registerView(nativeContainer, arrayListOf(icon, title, image, description, cta), createListener())
    }

    companion object {

        private fun createListener(): MediafyNativeAdUnitEventListener {
            return object : MediafyNativeAdUnitEventListener {
                override fun onAdImpression() {
                    // Called when ad displayed
                    Log.d(TAG, "Ad displayed on the screen")
                }

                override fun onAdClicked() {
                    // Called when ad clicked
                    Log.d(TAG, "Ad clicked")
                }

                override fun onAdExpired() {
                    // Called when ad expired
                    Log.d(TAG, "Ad expired")
                }
            }
        }
    }
}