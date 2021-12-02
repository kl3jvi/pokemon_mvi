package com.kl3jvi.crispytask.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Pokemon(
    var page: Int = 0,
    val name: String,
    val url: String,
    val image_Url: String
) : Parcelable
