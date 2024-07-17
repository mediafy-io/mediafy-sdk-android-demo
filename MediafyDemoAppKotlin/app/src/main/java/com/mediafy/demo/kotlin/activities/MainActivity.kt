/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediafy.demo.kotlin.R
import com.mediafy.demo.kotlin.databinding.ActivityMainBinding
import com.mediafy.demo.kotlin.testcases.*
import com.mediafy.demo.kotlin.utils.Settings

class MainActivity : AppCompatActivity() {

    private var integrationKind: IntegrationKind? = null
    private var adFormat: AdFormat? = null
    private var searchRequest = ""

    private lateinit var binding: ActivityMainBinding
    private lateinit var testCaseAdapter: TestCaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initSpinners()
        initSearch()
        initList()
    }


    private fun initSpinners() {
        binding.spinnerIntegrationKind.apply {
            adapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                IntegrationKind.values().map { it.adServer }.toMutableList().apply {
                    add(0, "All")
                }
            )
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, l: Long) {
                    integrationKind = if (position == 0) null else IntegrationKind.values()[position - 1]
                    Settings.get().lastIntegrationKindId = position
                    updateList()
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
            setSelection(Settings.get().lastIntegrationKindId)
        }
        binding.spinnerAdType.apply {
            adapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                AdFormat.values().map { it.description }.toMutableList().apply {
                    add(0, "All")
                }
            )
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, l: Long) {
                    adFormat = if (position == 0) null else AdFormat.values()[position - 1]
                    Settings.get().lastAdFormatId = position
                    updateList()
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
            setSelection(Settings.get().lastAdFormatId)
        }
    }

    private fun initSearch() {
        binding.etSearch.addTextChangedListener {
            searchRequest = it.toString()
            updateList()
        }
    }

    private fun initList() {
        binding.rvAdTypes.apply {
            testCaseAdapter = TestCaseAdapter { showAd(it) }
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = testCaseAdapter
        }
    }

    private fun updateList() {
        val list = TestCaseRepository.getList().filter {
            if (integrationKind != null && it.integrationKind != integrationKind) {
                return@filter false
            }

            if (adFormat != null && it.adFormat != adFormat) {
                return@filter false
            }

            if (searchRequest.isNotBlank() && !it.name.contains(searchRequest, ignoreCase = true)) {
                return@filter false
            }

            return@filter true
        }
        testCaseAdapter.setList(list)
    }

    private fun showAd(testCase: TestCase) {
        TestCaseRepository.lastTestCase = testCase
        startActivity(Intent(this, testCase.activity))
    }

}

