

/*
 * Mediafy SDK
 *
 * Copyright 2024 Mars Technologies Ltd. All rights reserved.
 */

package com.mediafy.demo.java.utils;

import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageUtils {

    public static void download(String url, ImageView imageView) {
        Glide.with(imageView)
            .load(url)
            .into(imageView);
    }

}
