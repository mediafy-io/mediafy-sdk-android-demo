

/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.testcases;

public enum AdFormat {

    DISPLAY_BANNER("Display Banner"),
    VIDEO_BANNER("Video Banner"),
    DISPLAY_INTERSTITIAL("Display Interstitial"),
    VIDEO_INTERSTITIAL("Video Interstitial"),
    VIDEO_REWARDED("Video Rewarded"),
    IN_STREAM_VIDEO("In-stream Video"),
    NATIVE("Native"),
    MULTIFORMAT("Multiformat");

    private final String description;

    AdFormat(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
