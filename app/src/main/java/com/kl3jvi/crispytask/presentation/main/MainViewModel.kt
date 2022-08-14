package com.kl3jvi.crispytask.presentation.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kl3jvi.crispytask.domain.use_case.get_pokemons.GetPokemonsUseCase
import com.kl3jvi.crispytask.utils.Result
import com.kl3jvi.crispytask.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {
    val pokemonUiState = getPokemonsUseCase(1).asResult().map { result ->
        when (result) {
            is Result.Error -> PokemonsUiState.PokemonsRetrievedError(result.exception)
            Result.Loading -> PokemonsUiState.PokemonsAreLoading
            is Result.Success -> PokemonsUiState.PokemonsRetrieved(result.data)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        PokemonsUiState.PokemonsAreLoading
    )


}