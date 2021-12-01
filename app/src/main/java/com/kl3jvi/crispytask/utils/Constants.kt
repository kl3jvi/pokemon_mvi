package com.kl3jvi.crispytask.utils

import android.content.Context
import android.content.res.ColorStateList
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.kl3jvi.crispytask.R

object Constants {
    const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val POKEMON_LIST = "pokemon"
    const val POKEMON_INFO = "pokemon/{name}"
    const val POKEMON_IMAGE_URL =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork"

    fun getColor(context: Context): ColorStateList {
        return ColorStateList.valueOf(ContextCompat.getColor(context,R.color.chipBg))
    }


}