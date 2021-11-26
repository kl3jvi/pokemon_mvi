package com.kl3jvi.crispytask.data.network

import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.kl3jvi.crispytask.domain.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): List<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): List<PokemonInfo>
}