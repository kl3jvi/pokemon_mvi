package com.kl3jvi.crispytask.data.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<PokemonInfoDto.TypeResponseDto>? {
        val listType = Types.newParameterizedType(List::class.java, PokemonInfoDto.TypeResponseDto::class.java)
        val adapter: JsonAdapter<List<PokemonInfoDto.TypeResponseDto>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<PokemonInfoDto.TypeResponseDto>?): String {
        val listType = Types.newParameterizedType(List::class.java, PokemonInfoDto.TypeResponseDto::class.java)
        val adapter: JsonAdapter<List<PokemonInfoDto.TypeResponseDto>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}