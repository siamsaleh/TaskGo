package com.siamsaleh.taskgo.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.siamsaleh.taskgo.R

fun ImageView.loadImage(context: Context, url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.search_bg)
        .error(R.drawable.btn_gradient_bg)
        .transition(DrawableTransitionOptions.withCrossFade()) // Fade transition
        .into(this)
}