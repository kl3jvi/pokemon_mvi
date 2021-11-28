package com.kl3jvi.crispytask.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kl3jvi.crispytask.R

object ViewBinding {
    /**
     * Extensions functions for image databiniding with GLIDE
     */
    @JvmStatic
    @BindingAdapter("image", "placeholder")
    fun setImage(image: ImageView, url: String?, placeHolder: Drawable) {
        if (!url.isNullOrEmpty()) {
            Glide.with(image.context).load(url).centerCrop()
                .placeholder(R.drawable.ic_no_image)
                .into(image)
        } else {
            image.setImageDrawable(placeHolder)
        }
    }
}