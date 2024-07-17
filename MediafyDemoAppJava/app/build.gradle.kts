/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.mediafy.demo.java"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mediafy.demo.java"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation("com.mediafy:mediafy-sdk:2.4.4")
    implementation("com.mediafy:mediafy-sdk-applovin-adapters:2.4.4")
    implementation("com.mediafy:mediafy-sdk-google-adapters:2.4.4")

    // Default
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Google ads
    implementation("com.google.android.gms:play-services-ads:23.0.0")

    // Applovin Max ads
    implementation("com.applovin:applovin-sdk:12.5.0")

    // Image Downloader
    implementation("com.github.bumptech.glide:glide:4.14.2")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
