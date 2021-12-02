package com.kl3jvi.crispytask.domain.use_case.get_pokemon

import com.kl3jvi.crispytask.data.model.toPokemonInfo
import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import com.kl3jvi.crispytask.utils.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(pokemonName: String): Flow<ResponseState<PokemonInfo>> = flow {
        try {
            emit(ResponseState.Loading<PokemonInfo>())
            val coins = repository.getPokemonByName(pokemonName).toPokemonInfo()
            emit(ResponseState.Success<PokemonInfo>(coins))
        } catch (e: HttpException) {
            emit(ResponseState.Error<PokemonInfo>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                ResponseState.Error<PokemonInfo>(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }.flowOn(ioDispatcher)
}