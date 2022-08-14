package com.kl3jvi.crispytask.domain.use_case.get_pokemons

import com.kl3jvi.crispytask.data.model.toPokemon
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(
        page: Int
    ): Flow<List<Pokemon>> {
        val pokemons = repository.getPokemons(page).map { pokemonList ->
            pokemonList.map { pokemonDto -> pokemonDto.toPokemon() }
        }
        return pokemons
    }
}