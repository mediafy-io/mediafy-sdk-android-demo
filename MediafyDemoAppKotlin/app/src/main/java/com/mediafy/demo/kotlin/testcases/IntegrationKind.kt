/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.testcases

enum class IntegrationKind(
    val adServer: String,
) {

    INAPP("In-App"),
    ADMOB("AdMob"),
    APPLOVIN("AppLovin MAX");

}