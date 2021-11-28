package com.kl3jvi.crispytask.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.kl3jvi.crispytask.R
import com.kl3jvi.crispytask.utils.GlideApp

object ViewBinding {
    /**
     * Iimage data biniding with GLIDE
     */
    @JvmStatic
    @BindingAdapter("image", "placeholder")
    fun setImage(image: ImageView, url: String?, placeHolder: Drawable) {
        if (!url.isNullOrEmpty()) {
            GlideApp.with(image.context).load(url).centerCrop()
                .placeholder(R.drawable.ic_no_image)
                .into(image)
        } else {
            image.setImageDrawable(placeHolder)
        }
    }
}