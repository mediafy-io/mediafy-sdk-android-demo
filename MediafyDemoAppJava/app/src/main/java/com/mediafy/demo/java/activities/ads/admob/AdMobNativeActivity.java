/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.admob;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.mediafy.demo.java.activities.BaseAdActivity;
import com.mediafy.demo.java.databinding.ViewNativeAdAdMobBinding;
import com.mediafy.sdk.api.adunits.nativead.request.*;
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker;
import com.mediafy.sdk.gad.MediafyGadMediationAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdMobNativeActivity extends BaseAdActivity {

    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-2844566227051243/1365562029";

    private AdLoader adLoader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }


    private void createAd() {
        // 1. Create native ad config
        MediafyNativeAdConfig nativeConfig = createNativeConfig();
        Bundle extrasBundle = nativeConfig.toGmaBundle();

        // 2.  Create ad request with MediafyGadMediationAdapter and native ad config
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(MediafyGadMediationAdapter.class, extrasBundle)
                .build();

        // 3. Configure ad loader
        adLoader = new AdLoader.Builder(this, ADMOB_AD_UNIT_ID)
                .forNativeAd((ad) -> {
                    // 5. Create native view
                    createCustomView(ad);
                })
                .withAdListener(createListener())
                .withNativeAdOptions(new NativeAdOptions.Builder().build())
                .build();

        // 4. Load ad
        adLoader.loadAd(adRequest);
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

    private void createCustomView(NativeAd ad) {
        ViewNativeAdAdMobBinding binding = ViewNativeAdAdMobBinding.inflate(LayoutInflater.from(this));

        binding.tvHeadline.setText(ad.getHeadline());
        binding.tvBody.setText(ad.getBody());
        NativeAd.Image icon = ad.getIcon();
        if (icon != null) {
            binding.imgIco.setImageDrawable(icon.getDrawable());
        }
        if (!ad.getImages().isEmpty()) {
            binding.imgMedia.setMediaContent(ad.getMediaContent());
        }

        NativeAdView container = binding.viewNativeWrapper;
        container.setHeadlineView(binding.tvHeadline);
        container.setBodyView(binding.tvBody);
        container.setIconView(binding.imgIco);
        container.setMediaView(binding.imgMedia);
        container.setNativeAd(ad);

        getContainerForAd().addView(binding.getRoot());
    }


    private static AdListener createListener() {
        return new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Ad loaded successfully");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.e(TAG, "Ad failed to load: " + loadAdError);
            }
        };
    }

}
