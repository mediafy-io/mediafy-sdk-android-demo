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
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder
import com.mediafy.demo.kotlin.R
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.sdk.api.adunits.nativead.request.*
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker.EventTrackingMethod
import com.mediafy.sdk.configuration.NativeAdUnitConfiguration
import java.util.*

class AppLovinNativeActivity : BaseAdActivity() {

    private var appLovinLoader: MaxNativeAdLoader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (appLovinLoader != null) {
            appLovinLoader!!.destroy()
        }
    }


    private fun createAd() {
        // 1. Create MaxNativeAdLoader
        appLovinLoader = MaxNativeAdLoader(APP_LOVIN_AD_UNIT_ID, this)
        appLovinLoader?.setNativeAdListener(createListener(containerForAd))

        // 2. Create native ad config and set it to local extras
        val nativeAdConfig = createNativeConfig()
        appLovinLoader?.setLocalExtraParameter(MediafyNativeAdConfig.KEY_EXTRAS, nativeAdConfig)

        // 3. Create custom native view
        val adView = createCustomView()

        // 4. Load ad
        appLovinLoader?.loadAd(adView)
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
    
    private fun createCustomView(): MaxNativeAdView {
        val builder: MaxNativeAdViewBinder = MaxNativeAdViewBinder.Builder(R.layout.view_native_ad_max)
            .setTitleTextViewId(R.id.tvHeadline)
            .setBodyTextViewId(R.id.tvBody)
            .setIconImageViewId(R.id.imgIco)
            .setMediaContentViewGroupId(R.id.frameMedia)
            .setCallToActionButtonId(R.id.btnCallToAction)
            .build()

        val adView = MaxNativeAdView(builder, this)
        return adView
    }

    companion object {
        private const val APP_LOVIN_AD_UNIT_ID = "d8056526e5554868"

        private fun createListener(containerForAd: ViewGroup): MaxNativeAdListener {
            return object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(maxNativeAdView: MaxNativeAdView?, maxAd: MaxAd) {
                    super.onNativeAdLoaded(maxNativeAdView, maxAd)

                    // 5. Show ad
                    containerForAd.removeAllViews()
                    containerForAd.addView(maxNativeAdView)
                }

                override fun onNativeAdLoadFailed(s: String, maxError: MaxError) {
                    Log.e(TAG, "Can't download native ad: " + maxError.message)
                }
            }
        }
    }
}