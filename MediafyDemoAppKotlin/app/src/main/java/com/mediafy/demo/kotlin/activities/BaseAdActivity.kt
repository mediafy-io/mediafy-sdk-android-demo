/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.activities

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mediafy.demo.kotlin.R
import com.mediafy.demo.kotlin.databinding.ActivityDemoBinding
import com.mediafy.demo.kotlin.testcases.TestCase
import com.mediafy.demo.kotlin.testcases.TestCaseRepository

open class BaseAdActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AdExampleActivity"
    }

    /**
     * ViewGroup container for any ad view.
     */
    protected val containerForAd: ViewGroup
        get() = binding.frameAdWrapper

    private lateinit var binding: ActivityDemoBinding
    private var testCase: TestCase = TestCaseRepository.lastTestCase

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)
        binding.tvTestCaseName.text = testCase.name
    }

}