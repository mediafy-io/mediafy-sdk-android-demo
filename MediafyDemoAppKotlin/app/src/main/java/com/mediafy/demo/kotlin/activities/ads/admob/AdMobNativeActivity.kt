/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.kotlin.activities.ads.admob

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.mediafy.demo.kotlin.activities.BaseAdActivity
import com.mediafy.demo.kotlin.databinding.ViewNativeAdAdMobBinding
import com.mediafy.sdk.api.adunits.nativead.request.*
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker.EventTrackingMethod
import com.mediafy.sdk.gad.MediafyGadMediationAdapter
import java.util.*

class AdMobNativeActivity : BaseAdActivity() {
    private var adLoader: AdLoader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAd()
    }


    private fun createAd() {
        // 1. Create native ad config
        val nativeConfig = createNativeConfig()
        val extrasBundle = nativeConfig.toGmaBundle()

        // 2.  Create ad request with MediafyGadMediationAdapter and native ad config
        val adRequest = AdRequest.Builder()
            .addNetworkExtrasBundle(MediafyGadMediationAdapter::class.java, extrasBundle)
            .build()

        // 3. Configure ad loader
        adLoader = AdLoader.Builder(this, ADMOB_AD_UNIT_ID)
            .forNativeAd { ad: NativeAd ->
                createCustomView(ad)
            }
            .withAdListener(createListener())
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .build()

        // 4. Load ad
        adLoader!!.loadAd(adRequest)
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


    private fun createCustomView(ad: NativeAd) {
        val binding = ViewNativeAdAdMobBinding.inflate(LayoutInflater.from(this))

        binding.tvHeadline.setText(ad.headline)
        binding.tvBody.setText(ad.body)
        val icon = ad.icon
        if (icon != null) {
            binding.imgIco.setImageDrawable(icon.drawable)
        }
        if (!ad.images.isEmpty()) {
            binding.imgMedia.setMediaContent(ad.mediaContent)
        }

        val container: NativeAdView = binding.viewNativeWrapper
        container.headlineView = binding.tvHeadline
        container.bodyView = binding.tvBody
        container.iconView = binding.imgIco
        container.mediaView = binding.imgMedia
        container.setNativeAd(ad)

        containerForAd.addView(binding.getRoot())
    }


    companion object {
        private const val ADMOB_AD_UNIT_ID = "ca-app-pub-2844566227051243/1365562029"

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