package com.kl3jvi.crispytask.domain.use_case.get_pokemons

import com.kl3jvi.crispytask.data.model.toPokemon
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import com.kl3jvi.crispytask.domain.states.PokemonResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<PokemonResponseState<List<Pokemon>>> = flow {
        try {
            emit(PokemonResponseState.Loading())
            val coins = repository.getPokemons().map { it.toPokemon() }
            emit(PokemonResponseState.Success(coins))
        } catch (e: HttpException) {
            emit(PokemonResponseState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(PokemonResponseState.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }
}