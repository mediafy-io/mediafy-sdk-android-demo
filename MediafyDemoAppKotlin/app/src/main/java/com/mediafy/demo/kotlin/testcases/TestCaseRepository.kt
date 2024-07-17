/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.testcases

import com.mediafy.demo.kotlin.activities.ads.admob.*
import com.mediafy.demo.kotlin.activities.ads.applovin.*
import com.mediafy.demo.kotlin.activities.ads.inapp.*

object TestCaseRepository {

    lateinit var lastTestCase: TestCase

    fun getList() = arrayListOf(

        /* In app cases */
        TestCase(
            "Banner 320x50 (Mediafy)",
            AdFormat.DISPLAY_BANNER,
            IntegrationKind.INAPP,
            MediafyBanner320x50Activity::class.java
        ),
        TestCase(
            "Banner 300x250 (Mediafy)",
            AdFormat.DISPLAY_BANNER,
            IntegrationKind.INAPP,
            MediafyBanner300x250Activity::class.java
        ),
        TestCase(
            "Banner Multisize (Mediafy)",
            AdFormat.DISPLAY_BANNER,
            IntegrationKind.INAPP,
            MediafyBannerMultisizeActivity::class.java
        ),
        TestCase(
            "Video Banner (Mediafy)",
            AdFormat.VIDEO_BANNER,
            IntegrationKind.INAPP,
            MediafyVideoBannerActivity::class.java
        ),
        TestCase(
            "Display Interstitial (Mediafy)",
            AdFormat.DISPLAY_INTERSTITIAL,
            IntegrationKind.INAPP,
            MediafyInterstitialActivity::class.java
        ),
        TestCase(
            "Video Interstitial (Mediafy)",
            AdFormat.VIDEO_INTERSTITIAL,
            IntegrationKind.INAPP,
            MediafyVideoInterstitialActivity::class.java
        ),
        TestCase(
            "Interstitial Multiformat (Mediafy)",
            AdFormat.MULTIFORMAT,
            IntegrationKind.INAPP,
            MediafyInterstitialMultiformatActivity::class.java
        ),
        TestCase(
            "Native (Mediafy)",
            AdFormat.NATIVE,
            IntegrationKind.INAPP,
            MediafyNativeActivity::class.java
        ),


        /* AdMob */
        TestCase(
            "Banner 320x50 (AdMob)",
            AdFormat.DISPLAY_BANNER,
            IntegrationKind.ADMOB,
            AdMobBanner320x50Activity::class.java
        ),
        TestCase(
            "Banner 300x250 (AdMob)",
            AdFormat.DISPLAY_BANNER,
            IntegrationKind.ADMOB,
            AdMobBanner300x250Activity::class.java
        ),
        TestCase(
            "Video Banner (AdMob)",
            AdFormat.VIDEO_BANNER,
            IntegrationKind.ADMOB,
            AdMobVideoBannerActivity::class.java
        ),
        TestCase(
            "Display Interstitial (AdMob)",
            AdFormat.DISPLAY_INTERSTITIAL,
            IntegrationKind.ADMOB,
            AdMobInterstitialActivity::class.java
        ),
        TestCase(
            "Video Interstitial (AdMob)",
            AdFormat.VIDEO_INTERSTITIAL,
            IntegrationKind.ADMOB,
            AdMobVideoInterstitialActivity::class.java
        ),
        TestCase(
            "Interstitial Multiformat (AdMob)",
            AdFormat.MULTIFORMAT,
            IntegrationKind.ADMOB,
            AdMobInterstitialMultiformatActivity::class.java
        ),
        TestCase(
            "Native (AdMob)",
            AdFormat.NATIVE,
            IntegrationKind.ADMOB,
            AdMobNativeActivity::class.java
        ),


        /* AppLovin */
        TestCase(
            "Banner 320x50 (AppLovin)",
            AdFormat.DISPLAY_BANNER,
            IntegrationKind.APPLOVIN,
            AppLovinBanner320x50Activity::class.java
        ),
        TestCase(
            "Banner 300x250 (AppLovin)",
            AdFormat.DISPLAY_BANNER,
            IntegrationKind.APPLOVIN,
            AppLovinBanner300x250Activity::class.java
        ),
        TestCase(
            "Video Banner (AppLovin)",
            AdFormat.VIDEO_BANNER,
            IntegrationKind.APPLOVIN,
            AppLovinVideoBannerActivity::class.java
        ),
        TestCase(
            "Display Interstitial (AppLovin)",
            AdFormat.DISPLAY_INTERSTITIAL,
            IntegrationKind.APPLOVIN,
            AppLovinInterstitialActivity::class.java
        ),
        TestCase(
            "Video Interstitial (AppLovin)",
            AdFormat.VIDEO_INTERSTITIAL,
            IntegrationKind.APPLOVIN,
            AppLovinVideoInterstitialActivity::class.java
        ),
        TestCase(
            "Interstitial Multiformat (AppLovin)",
            AdFormat.MULTIFORMAT,
            IntegrationKind.APPLOVIN,
            AppLovinInterstitialMultiformatActivity::class.java
        ),
        TestCase(
            "Native (AppLovin)",
            AdFormat.NATIVE,
            IntegrationKind.APPLOVIN,
            AppLovinNativeActivity::class.java
        )
    )

}