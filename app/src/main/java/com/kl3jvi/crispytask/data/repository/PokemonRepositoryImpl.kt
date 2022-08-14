package com.kl3jvi.crispytask.data.repository


import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.data.network.PokemonApiClient
import com.kl3jvi.crispytask.data.persistence.PokemonDao
import com.kl3jvi.crispytask.data.persistence.PokemonInfoDao
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApiClient,
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao,
    private val ioDispatcher: CoroutineDispatcher
) : PokemonRepository {

    override fun getPokemons(page: Int) = flow {
        val pokemonList = pokemonDao.getPokemonList().ifEmpty {
            withContext(ioDispatcher) {
                val response = api.fetchPokemonList(page).results
                response.forEach { pokemon -> pokemon.page = page }
                pokemonDao.insertPokemonList(response)
                pokemonDao.getPokemonList()
            }
        }
        emit(pokemonList)
    }

    override suspend fun getPokemonByName(pokemonName: String): PokemonInfoDto {
        return pokemonInfoDao.getPokemonInfo(pokemonName)
            ?: withContext(ioDispatcher) {
                val response = api.fetchPokemonDetails(name = pokemonName)
                pokemonInfoDao.insertPokemonInfo(response)
                response
            }
    }
}