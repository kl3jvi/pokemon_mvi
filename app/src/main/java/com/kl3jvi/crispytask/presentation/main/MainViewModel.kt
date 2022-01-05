package com.kl3jvi.crispytask.presentation.main


import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.kl3jvi.crispytask.domain.use_case.get_pokemons.GetPokemonsUseCase
import com.kl3jvi.crispytask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : AndroidDataFlow() {

    init {
        getPokemonList()
    }

    var isLoading: Boolean = false

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    @ExperimentalCoroutinesApi
    private fun getPokemonList() = action {
        pokemonFetchingIndex.flatMapLatest { page ->
            getPokemonsUseCase(
                page,
                onStart = { isLoading = true },
                onComplete = { isLoading = false },
            ).onEach { result ->
                when (result) {
                    is ResponseState.Success -> setState {
                        PokemonListState.PokemonsRetrieved(pokemons = result.data ?: emptyList())
                    }

                    is ResponseState.Error -> setState {
                        PokemonListState.PokemonsRetrievedError(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is ResponseState.Loading -> setState { PokemonListState.PokemonsAreLoading }
                }
            }
        }.launchIn(viewModelScope)
    }

    @MainThread
    fun fetchNextPokemonList() {
        if (!isLoading) {
            pokemonFetchingIndex.value++
        }
    }
}