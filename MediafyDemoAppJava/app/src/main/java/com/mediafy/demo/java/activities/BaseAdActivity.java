

/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.mediafy.demo.java.R;
import com.mediafy.demo.java.databinding.ActivityDemoBinding;
import com.mediafy.demo.java.testcases.TestCase;
import com.mediafy.demo.java.testcases.TestCaseRepository;

public class BaseAdActivity extends AppCompatActivity {

    protected static final String TAG = "AdExampleActivity";
    protected ActivityDemoBinding binding;

    private TestCase testCase = TestCaseRepository.lastTestCase;

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        binding.tvTestCaseName.setText(testCase.getName());
    }

    protected ViewGroup getContainerForAd() {
        return binding.frameAdWrapper;
    }

}
