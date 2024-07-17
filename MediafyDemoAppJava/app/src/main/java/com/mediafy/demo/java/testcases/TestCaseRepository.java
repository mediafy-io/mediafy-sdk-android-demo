/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.testcases;


import com.mediafy.demo.java.activities.ads.admob.*;
import com.mediafy.demo.java.activities.ads.applovin.*;
import com.mediafy.demo.java.activities.ads.inapp.*;

import java.util.Arrays;
import java.util.List;

public class TestCaseRepository {

    public static TestCase lastTestCase;

    public static List<TestCase> getList() {
        List<TestCase> result = Arrays.asList(
                /* In app cases */
                new TestCase(
                        "Banner 320x50 (Mediafy)",
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.INAPP,
                        MediafyBanner320x50Activity.class
                ),
                new TestCase(
                        "Banner 300x250 (Mediafy)",
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.INAPP,
                        MediafyBanner300x250Activity.class
                ),
                new TestCase(
                        "Banner Multisize (Mediafy)",
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.INAPP,
                        MediafyBannerMultisizeActivity.class
                ),
                new TestCase(
                        "Video Banner (Mediafy)",
                        AdFormat.VIDEO_BANNER,
                        IntegrationKind.INAPP,
                        MediafyVideoBannerActivity.class
                ),
                new TestCase(
                        "Display Interstitial (Mediafy)",
                        AdFormat.DISPLAY_INTERSTITIAL,
                        IntegrationKind.INAPP,
                        MediafyInterstitialActivity.class
                ),
                new TestCase(
                        "Video Interstitial (Mediafy)",
                        AdFormat.VIDEO_INTERSTITIAL,
                        IntegrationKind.INAPP,
                        MediafyVideoInterstitialActivity.class
                ),
                new TestCase(
                        "Interstitial Multiformat (Mediafy)",
                        AdFormat.MULTIFORMAT,
                        IntegrationKind.INAPP,
                        MediafyInterstitialMultiformatActivity.class
                ),
                new TestCase(
                        "Native (Mediafy)",
                        AdFormat.NATIVE,
                        IntegrationKind.INAPP,
                        MediafyNativeActivity.class
                ),

                /* AdMob */
                new TestCase(
                        "Banner 300x250 (AdMob)",
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.ADMOB,
                        AdMobBanner300x250Activity.class
                ),
                new TestCase(
                        "Banner 320x50 (AdMob)",
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.ADMOB,
                        AdMobBanner320x50Activity.class
                ),
                new TestCase(
                        "Video Banner (AdMob)",
                        AdFormat.VIDEO_BANNER,
                        IntegrationKind.ADMOB,
                        AdMobVideoBannerActivity.class
                ),
                new TestCase(
                        "Display Interstitial (AdMob)",
                        AdFormat.DISPLAY_INTERSTITIAL,
                        IntegrationKind.ADMOB,
                        AdMobInterstitialActivity.class
                ),
                new TestCase(
                        "Video Interstitial (AdMob)",
                        AdFormat.VIDEO_INTERSTITIAL,
                        IntegrationKind.ADMOB,
                        AdMobVideoInterstitialActivity.class
                ),
                new TestCase(
                        "Interstitial Multiformat (AdMob)",
                        AdFormat.MULTIFORMAT,
                        IntegrationKind.ADMOB,
                        AdMobInterstitialMultiformatActivity.class
                ),
                new TestCase(
                        "Native (AdMob)",
                        AdFormat.NATIVE,
                        IntegrationKind.ADMOB,
                        AdMobNativeActivity.class
                ),

                /* AppLovin */
                new TestCase(
                        "Banner 300x250 (AppLovin)",
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.APPLOVIN,
                        AppLovinBanner300x250Activity.class
                ),
                new TestCase(
                        "Banner 320x50 (AppLovin)",
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.APPLOVIN,
                        AppLovinBanner320x50Activity.class
                ),
                new TestCase(
                        "Video Banner (AppLovin)",
                        AdFormat.VIDEO_BANNER,
                        IntegrationKind.APPLOVIN,
                        AppLovinVideoBannerActivity.class
                ),
                new TestCase(
                        "Display Interstitial (AppLovin)",
                        AdFormat.DISPLAY_INTERSTITIAL,
                        IntegrationKind.APPLOVIN,
                        AppLovinInterstitialActivity.class
                ),
                new TestCase(
                        "Video Interstitial (AppLovin)",
                        AdFormat.VIDEO_INTERSTITIAL,
                        IntegrationKind.APPLOVIN,
                        AppLovinVideoInterstitialActivity.class
                ),
                new TestCase(
                        "Interstitial Multiformat (AppLovin)",
                        AdFormat.MULTIFORMAT,
                        IntegrationKind.APPLOVIN,
                        AppLovinInterstitialMultiformatActivity.class
                ),
                new TestCase(
                        "Native (AppLovin)",
                        AdFormat.NATIVE,
                        IntegrationKind.APPLOVIN,
                        AppLovinNativeActivity.class
                )
        );
        return result;
    }

}
