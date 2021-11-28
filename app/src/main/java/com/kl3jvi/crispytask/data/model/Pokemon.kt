package com.kl3jvi.crispytask.data.model

import com.kl3jvi.crispytask.utils.Constants.POKEMON_IMAGE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "$POKEMON_IMAGE_URL/$index.png"
    }
}