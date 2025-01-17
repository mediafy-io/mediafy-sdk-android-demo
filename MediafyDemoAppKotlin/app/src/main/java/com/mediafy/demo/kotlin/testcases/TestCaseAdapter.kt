/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.testcases

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mediafy.demo.kotlin.databinding.ListItemAdTypeBinding

class TestCaseAdapter(
    private val onItemClicked: (TestCase) -> Unit,
) : RecyclerView.Adapter<TestCaseAdapter.AdTypeViewHolder>() {

    private var list: List<TestCase> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdTypeViewHolder {
        val binding = ListItemAdTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        val viewHolder = AdTypeViewHolder(binding)
        viewHolder.setOnItemClickedListener(onItemClicked)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdTypeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<TestCase>) {
        this.list = list
        notifyDataSetChanged()
    }


    class AdTypeViewHolder(
        private val binding: ListItemAdTypeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var testCase: TestCase? = null

        fun bind(testCase: TestCase) {
            this.testCase = testCase
            binding.tvName.text = testCase.name
        }

        fun setOnItemClickedListener(onItemClicked: (TestCase) -> Unit) {
            binding.root.setOnClickListener {
                onItemClicked(testCase ?: return@setOnClickListener)
            }
        }

    }

}