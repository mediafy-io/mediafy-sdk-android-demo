

/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.testcases;

public enum IntegrationKind {

    INAPP("In-App"),
    ADMOB("AdMob"),
    APPLOVIN("AppLovin MAX");

    private final String adServer;

    IntegrationKind(String adServer) {
        this.adServer = adServer;
    }

    public String getAdServer() {
        return adServer;
    }
}
