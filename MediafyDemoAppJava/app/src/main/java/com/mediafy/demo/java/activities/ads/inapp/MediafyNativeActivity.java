/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities.ads.inapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.mediafy.demo.java.R;
import com.mediafy.demo.java.activities.BaseAdActivity;
import com.mediafy.demo.java.utils.ImageUtils;
import com.mediafy.sdk.api.adunits.MediafyNativeAdUnit;
import com.mediafy.sdk.api.adunits.MediafyNativeAdUnitEventListener;
import com.mediafy.sdk.api.adunits.nativead.request.*;
import com.mediafy.sdk.api.adunits.nativead.response.MediafyNativeAd;
import com.mediafy.sdk.api.adunits.nativead.response.NativeEventTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediafyNativeActivity extends BaseAdActivity {

    private MediafyNativeAdUnit adUnit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adUnit != null) {
            adUnit.destroy();
        }
    }


    private void createAd() {
        // 1. Create MediafyNativeAdUnit
        adUnit = new MediafyNativeAdUnit();

        // 2. Configure ad unit with native config
        adUnit.setNativeAdConfig(createNativeConfig());

        // 3. Load ad
        adUnit.loadAd((result) -> {
            MediafyNativeAd nativeAd = result.getNativeAd();
            if (nativeAd == null) {
                Log.e("AdExample", "Native ad is null: " + result.getStatus());
                return;
            }

            Log.d(TAG, "Native ad loaded successfully");
            // 4. Create native view
            createNativeView(nativeAd);
        });
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

    private void createNativeView(MediafyNativeAd ad) {
        View nativeContainer = View.inflate(this, R.layout.layout_native, null);

        ImageView icon = nativeContainer.findViewById(R.id.imgIcon);
        ImageUtils.download(ad.getIconUrl(), icon);

        TextView title = nativeContainer.findViewById(R.id.tvTitle);
        title.setText(ad.getTitle());

        ImageView image = nativeContainer.findViewById(R.id.imgImage);
        ImageUtils.download(ad.getImageUrl(), image);

        TextView description = nativeContainer.findViewById(R.id.tvDesc);
        description.setText(ad.getDescription());

        Button cta = nativeContainer.findViewById(R.id.btnCta);
        cta.setText(ad.getCallToAction());
        getContainerForAd().addView(nativeContainer);

        ad.registerView(nativeContainer, Arrays.asList(icon, title, image, description, cta), createListener());
    }

    private static MediafyNativeAdUnitEventListener createListener() {
        return new MediafyNativeAdUnitEventListener() {
            @Override
            public void onAdImpression() {
                // Called when ad displayed
                Log.d(TAG, "Ad displayed on the screen");
            }

            @Override
            public void onAdClicked() {
                // Called when ad clicked
                Log.d(TAG, "Ad clicked");
            }

            @Override
            public void onAdExpired() {
                // Called when ad expired
                Log.d(TAG, "Ad expired");
            }
        };
    }

}
