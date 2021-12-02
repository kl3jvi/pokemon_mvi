package com.kl3jvi.crispytask.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.utils.Constants.POKEMON_IMAGE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonDto(
    var page: Int = 0,
    @field:Json(name = "name") @PrimaryKey val name: String,
    @field:Json(name = "url") val url: String
) : Parcelable {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "$POKEMON_IMAGE_URL/$index.png"
    }
}

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        url = url,
        image_Url = getImageUrl()
    )
}