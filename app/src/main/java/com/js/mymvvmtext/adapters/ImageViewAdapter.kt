package com.js.mymvvmtext.adapters

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

/**
 * @author: double
 * @date: 19-9-24
 * @description:
 */
@BindingAdapter("setImageUrl")
fun setImageUrl(view: ImageView, @DrawableRes redId: Int) {

    view.setImageResource(redId)
}