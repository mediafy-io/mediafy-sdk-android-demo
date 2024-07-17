/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.applovin;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.mediafy.demo.java.R;
import com.mediafy.demo.java.activities.BaseAdActivity;
import com.mediafy.sdk.api.adunits.nativead.request.*;
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker;
import com.mediafy.sdk.configuration.NativeAdUnitConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppLovinNativeActivity extends BaseAdActivity {

    private static final String APP_LOVIN_AD_UNIT_ID = "7a8c0230c0b2e24a";

    private MaxNativeAdLoader appLovinLoader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (appLovinLoader != null) {
            appLovinLoader.destroy();
        }
    }


    private void createAd() {
        // 1. Create MaxNativeAdLoader
        appLovinLoader = new MaxNativeAdLoader(APP_LOVIN_AD_UNIT_ID, this);
        appLovinLoader.setNativeAdListener(createListener(getContainerForAd()));

        // 2. Create native ad config and set it to local extras
        MediafyNativeAdConfig nativeAdConfig = createNativeConfig();
        appLovinLoader.setLocalExtraParameter(MediafyNativeAdConfig.KEY_EXTRAS, nativeAdConfig);

        // 3. Create custom native view
        MaxNativeAdView adView = createCustomView();

        // 4. Load ad
        appLovinLoader.loadAd(adView);
    }




    private MediafyNativeAdConfig createNativeConfig() {
        NativeDataAsset ctaText = new NativeDataAsset(NativeDataAsset.DataType.CTATEXT);
        ctaText.setLen(15);

        NativeTitleAsset title = new NativeTitleAsset(90);
        title.setRequired(true);

        NativeImageAsset icon = new NativeImageAsset();
        icon.setHMin(50);
        icon.setWMin(50);
        icon.setImageType(NativeImageAsset.ImageType.ICON);

        NativeImageAsset mainImage = new NativeImageAsset();
        mainImage.setW(1200);
        mainImage.setH(627);
        mainImage.setImageType(NativeImageAsset.ImageType.MAIN);
        mainImage.setRequired(true);

        NativeDataAsset rating = new NativeDataAsset(NativeDataAsset.DataType.RATING);

        NativeDataAsset description = new NativeDataAsset(NativeDataAsset.DataType.DESC);
        description.setRequired(true);
        description.setLen(150);

        List<NativeAsset> assets = Arrays.asList(ctaText, title, icon, mainImage, rating, description);


        ArrayList<NativeEventTracker.EventTrackingMethod> eventTrackingMethods = new ArrayList<>(
                Arrays.asList(NativeEventTracker.EventTrackingMethod.IMAGE, NativeEventTracker.EventTrackingMethod.JS)
        );
        NativeEventTracker eventTracker = new NativeEventTracker(NativeEventTracker.EventType.IMPRESSION, eventTrackingMethods);
        ArrayList<NativeEventTracker> eventTrackers = new ArrayList<>();
        eventTrackers.add(eventTracker);


        MediafyNativeAdConfig config = new MediafyNativeAdConfig();
        config.setContextType(NativeContextType.SOCIAL_CENTRIC);
        config.setPlacementType(NativePlacementType.CONTENT_FEED);
        config.setContextSubType(NativeContextSubtype.GENERAL_SOCIAL);
        config.setNativeAssets(assets);
        config.setNativeEventTrackers(eventTrackers);
        return config;
    }

    private MaxNativeAdView createCustomView() {
        MaxNativeAdViewBinder builder = new MaxNativeAdViewBinder.Builder(R.layout.view_native_ad_max)
                .setTitleTextViewId(R.id.tvHeadline)
                .setBodyTextViewId(R.id.tvBody)
                .setIconImageViewId(R.id.imgIco)
                .setMediaContentViewGroupId(R.id.frameMedia)
                .setCallToActionButtonId(R.id.btnCallToAction)
                .build();

        MaxNativeAdView adView = new MaxNativeAdView(builder, this);
        return adView;
    }

    private static MaxNativeAdListener createListener(ViewGroup containerForAd) {
        return new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(@Nullable MaxNativeAdView maxNativeAdView, @NonNull MaxAd maxAd) {
                super.onNativeAdLoaded(maxNativeAdView, maxAd);

                // 5. Show ad
                containerForAd.removeAllViews();
                containerForAd.addView(maxNativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(@NonNull String s, @NonNull MaxError maxError) {
                Log.e(TAG, "Can't download native ad: " + maxError.getMessage());
            }
        };
    }

}
