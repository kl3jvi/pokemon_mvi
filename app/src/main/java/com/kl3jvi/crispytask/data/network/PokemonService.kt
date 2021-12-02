package com.kl3jvi.crispytask.data.network

import com.kl3jvi.crispytask.data.model.PokemonDto
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.data.model.PokemonResponseDto
import com.kl3jvi.crispytask.utils.Constants.POKEMON_INFO
import com.kl3jvi.crispytask.utils.Constants.POKEMON_LIST
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET(POKEMON_LIST)
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonResponseDto

    @GET(POKEMON_INFO)
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): PokemonInfoDto
}