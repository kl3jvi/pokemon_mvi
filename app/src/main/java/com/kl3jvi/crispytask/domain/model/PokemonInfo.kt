package com.kl3jvi.crispytask.domain.model

import com.kl3jvi.crispytask.data.model.PokemonInfoDto

data class PokemonInfo(
    val id: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<PokemonInfoDto.TypeResponse>,
) {
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)

}
