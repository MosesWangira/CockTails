package com.mosesaltruism.cocktails.core.common.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mosesaltruism.cocktails.R

//display image using glide
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).error(R.drawable.ic_launcher_background).into(imageView)
}