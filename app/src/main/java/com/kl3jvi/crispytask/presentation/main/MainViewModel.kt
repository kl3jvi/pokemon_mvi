package com.kl3jvi.crispytask.presentation.main


import androidx.lifecycle.viewModelScope
import com.kl3jvi.crispytask.domain.use_case.get_pokemons.GetPokemonsUseCase
import com.kl3jvi.crispytask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
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

    private fun getPokemonList() = action {
        getPokemonsUseCase(2).onEach { result ->
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
        }.launchIn(viewModelScope)
    }
}