package com.kl3jvi.crispytask.data.repository

import com.kl3jvi.crispytask.data.model.PokemonDto
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.data.network.PokemonApiClient
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApiClient
) : PokemonRepository {
    override suspend fun getPokemons(): List<PokemonDto> {
        return api.fetchPokemonList()
    }

    override suspend fun getPokemonByName(pokemonName: String): PokemonInfoDto {
        return api.fetchPokemonDetails(pokemonName)
    }
}