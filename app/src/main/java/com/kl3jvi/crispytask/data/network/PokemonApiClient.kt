package com.kl3jvi.crispytask.data.network

import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.data.model.PokemonResponseDto
import javax.inject.Inject

class PokemonApiClient @Inject constructor(
    private val pokemonService: PokemonService
) {
    suspend fun fetchPokemonList(page: Int): PokemonResponseDto =
        pokemonService.fetchPokemonList(
            limit = SIZE,
            offset = page * SIZE
        )

    suspend fun fetchPokemonDetails(name: String): PokemonInfoDto =
        pokemonService.fetchPokemonInfo(name = name)

    companion object {
        private const val SIZE = 20
    }
}
