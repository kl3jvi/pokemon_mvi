package com.kl3jvi.crispytask.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
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

    @JvmStatic
    @BindingAdapter("paletteImage", "paletteCard")
    fun bindLoadImagePalette(view: AppCompatImageView, url: String, paletteCard: MaterialCardView) {
        GlideApp.with(view.context)
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val rgb = palette?.dominantSwatch?.rgb
                        if (rgb != null) {
                            paletteCard.setCardBackgroundColor(rgb)
                        }
                    }.crossfade(true)
            ).into(view)
    }
}