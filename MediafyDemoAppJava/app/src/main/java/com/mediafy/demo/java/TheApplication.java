

/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java;

import android.app.Application;
import android.util.Log;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkInitializationConfiguration;
import com.google.android.gms.ads.MobileAds;
import com.mediafy.demo.java.utils.Settings;
import com.mediafy.sdk.api.Mediafy;


public class TheApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Settings.init(this);
        initMediafySdk();
        initAdMob();
        initApplovinMax();
    }

    public void initMediafySdk() {
        Mediafy.initializeSdk(this, "bid=56maxa");
    }

    public void initAdMob() {
        MobileAds.initialize(this);
    }

    public void initApplovinMax() {
        AppLovinSdkInitializationConfiguration config = AppLovinSdkInitializationConfiguration
                .builder(
                        "1tLUnP4cVQqpHuHH2yMtfdESvvUhTB05NdbCoDTceDDNVnhd_T8kwIzXDN9iwbdULTboByF-TtNaiTmsoVbxZw",
                        this
                )
                .setMediationProvider(AppLovinMediationProvider.MAX)
                .build();

        AppLovinSdk.getInstance(this).initialize(config, (it) -> {
            Log.d("ApplovinInitialization", "Initialization result: " + it);
        });
    }

}
