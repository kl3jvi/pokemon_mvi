package com.kl3jvi.crispytask.domain.use_case.get_pokemons

import com.kl3jvi.crispytask.data.model.toPokemon
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import com.kl3jvi.crispytask.utils.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
    ): Flow<ResponseState<List<Pokemon>>> = flow {
        try {
            emit(ResponseState.Loading<List<Pokemon>>())
            val pokemons = repository.getPokemons(page).map { it.toPokemon() }
            emit(ResponseState.Success<List<Pokemon>>(pokemons))
        } catch (e: HttpException) {
            emit(
                ResponseState.Error<List<Pokemon>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                ResponseState.Error<List<Pokemon>>(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}