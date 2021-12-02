package com.kl3jvi.crispytask.domain.model

import android.os.Parcelable
import androidx.room.Entity
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity
@Parcelize
data class PokemonInfo(
    val id: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: @RawValue List<TypeResponse>,
) : Parcelable {
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)

    @Parcelize
    data class TypeResponse(
        val slot: Int,
        val type: @RawValue PokemonInfoDto.TypeDto
    ) : Parcelable

    @Parcelize
    data class Type(
        val name: String
    ) : Parcelable
}
