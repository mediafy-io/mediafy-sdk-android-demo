

/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.testcases;

public class TestCase {

    private final String name;
    private final AdFormat adFormat;
    private final IntegrationKind integrationKind;
    private final Class<?> activity;

    public TestCase(
        String name,
        AdFormat adFormat,
        IntegrationKind integrationKind,
        Class<?> activity
    ) {
        this.name = name;
        this.adFormat = adFormat;
        this.integrationKind = integrationKind;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public AdFormat getAdFormat() {
        return adFormat;
    }

    public IntegrationKind getIntegrationKind() {
        return integrationKind;
    }

    public Class<?> getActivity() {
        return activity;
    }
}
