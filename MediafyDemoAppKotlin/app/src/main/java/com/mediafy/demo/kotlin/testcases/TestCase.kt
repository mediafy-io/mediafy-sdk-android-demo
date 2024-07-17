/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.testcases

import android.app.Activity

data class TestCase(
    val name: String,
    val adFormat: AdFormat,
    val integrationKind: IntegrationKind,
    val activity: Class<out Activity>,
)
