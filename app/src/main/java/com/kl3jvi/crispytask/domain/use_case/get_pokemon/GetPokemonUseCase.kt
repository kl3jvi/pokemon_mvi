package com.kl3jvi.crispytask.domain.use_case.get_pokemon

import com.kl3jvi.crispytask.data.model.toPokemonInfo
import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import com.kl3jvi.crispytask.domain.states.PokemonResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(pokemonName: String): Flow<PokemonResponseState<PokemonInfo>> = flow {
        try {
            emit(PokemonResponseState.Loading())
            val coins = repository.getPokemonByName(pokemonName).toPokemonInfo()
            emit(PokemonResponseState.Success(coins))
        } catch (e: HttpException) {
            emit(PokemonResponseState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                PokemonResponseState.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }
}