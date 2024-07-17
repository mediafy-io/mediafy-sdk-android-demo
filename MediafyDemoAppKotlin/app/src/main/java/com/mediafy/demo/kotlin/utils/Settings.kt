/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class Settings private constructor(
    private val preferences: SharedPreferences,
) {

    companion object {
        private const val KEY_LAST_INTEGRATION_KIND = "KEY_LAST_INTEGRATION_KIND"
        private const val KEY_LAST_AD_FORMAT = "KEY_LAST_AD_FORMAT"

        private var INSTANCE: Settings? = null

        fun init(context: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            INSTANCE = Settings(preferences)
        }

        fun get(): Settings {
            return INSTANCE ?: throw NullPointerException()
        }
    }

    var lastIntegrationKindId: Int
        get() = preferences.getInt(KEY_LAST_INTEGRATION_KIND, 1)
        set(value) {
            preferences.edit().putInt(KEY_LAST_INTEGRATION_KIND, value).apply()
        }

    var lastAdFormatId: Int
        get() = preferences.getInt(KEY_LAST_AD_FORMAT, 0)
        set(value) {
            preferences.edit().putInt(KEY_LAST_AD_FORMAT, value).apply()
        }

}