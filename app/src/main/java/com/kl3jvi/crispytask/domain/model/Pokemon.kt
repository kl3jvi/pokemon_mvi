package com.kl3jvi.crispytask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val url: String,
    val image_Url: String
) : Parcelable
