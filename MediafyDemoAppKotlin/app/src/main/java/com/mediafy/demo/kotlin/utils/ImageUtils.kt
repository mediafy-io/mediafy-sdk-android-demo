/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */



package com.mediafy.demo.kotlin.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {

    fun download(url: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }

}