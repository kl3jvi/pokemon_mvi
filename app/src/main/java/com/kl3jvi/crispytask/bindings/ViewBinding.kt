package com.kl3jvi.crispytask.bindings

import android.graphics.drawable.Drawable
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import com.kl3jvi.crispytask.R
import com.kl3jvi.crispytask.utils.GlideApp

object ViewBinding {
    /**
     * Image data binding with GLIDE
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

    /**
     * GlidePalette data binding
     */
    @JvmStatic
    @BindingAdapter("paletteImage", "paletteView")
    fun bindLoadImagePaletteView(view: AppCompatImageView, url: String, paletteView: View) {
        val context = view.context
        Glide.with(context)
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val domain = palette?.dominantSwatch?.rgb
                        if (domain != null) {
                            if(paletteView is MaterialCardView)
                            paletteView.setCardBackgroundColor(domain)
                        }
                    }.crossfade(true)
            ).into(view)
    }
}