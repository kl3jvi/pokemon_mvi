package com.kl3jvi.crispytask.domain.use_case.get_pokemon

import com.kl3jvi.crispytask.data.model.toPokemonInfo
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(pokemonName: String) = flow {
        val pokemon = repository.getPokemonByName(pokemonName).toPokemonInfo()
        emit(pokemon)
    }.flowOn(ioDispatcher)
}