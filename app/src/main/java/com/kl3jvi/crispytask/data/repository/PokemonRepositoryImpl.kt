package com.kl3jvi.crispytask.data.repository

import com.kl3jvi.crispytask.data.model.PokemonDto
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.data.network.PokemonApiClient
import com.kl3jvi.crispytask.data.persistence.PokemonDao
import com.kl3jvi.crispytask.data.persistence.PokemonInfoDao
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApiClient,
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao
) : PokemonRepository {
    override suspend fun getPokemons(page: Int): List<PokemonDto> {
        var pokemons = pokemonDao.getPokemonList(page)
        return if (pokemons.isEmpty()) {
            val response = api.fetchPokemonList().results
            pokemons = response
            pokemons.forEach { pokemon -> pokemon.page = page }
            pokemonDao.insertPokemonList(pokemons)

            pokemonDao.getAllPokemonList(page)
        } else {
            (pokemonDao.getAllPokemonList(page))
        }
    }

    override suspend fun getPokemonByName(pokemonName: String): PokemonInfoDto {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(pokemonName)
        return if (pokemonInfo == null) {
            val response = api.fetchPokemonDetails(name = pokemonName)
            pokemonInfoDao.insertPokemonInfo(response)
            response
        }else {
            pokemonInfo
        }
    }
}