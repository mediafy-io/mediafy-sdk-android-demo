/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin

import android.app.Application
import android.util.Log
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkInitializationConfiguration
import com.google.android.gms.ads.MobileAds
import com.mediafy.demo.kotlin.utils.Settings
import com.mediafy.sdk.api.Mediafy
import com.mediafy.sdk.rendering.networking.urlBuilder.BidPathBuilder

class TheApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initMediafySdk()
        initAdMob()
        initApplovinMax()
        Settings.init(this)
    }

    private fun initMediafySdk() {
        Mediafy.initializeSdk(this, "bid=56maxa")
    }

    private fun initAdMob() {
        MobileAds.initialize(this)
    }

    private fun initApplovinMax() {
        val initConfig = AppLovinSdkInitializationConfiguration
            .builder(
                "1tLUnP4cVQqpHuHH2yMtfdESvvUhTB05NdbCoDTceDDNVnhd_T8kwIzXDN9iwbdULTboByF-TtNaiTmsoVbxZw",
                this
            )
            .setMediationProvider(AppLovinMediationProvider.MAX)
            .build()

        AppLovinSdk.getInstance(this).initialize(initConfig) {
            Log.d("ApplovinInitialization", "Initialization result: $it")
        }
    }

}