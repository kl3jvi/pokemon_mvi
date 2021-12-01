package com.kl3jvi.crispytask.presentation.details

import com.kl3jvi.crispytask.domain.model.PokemonInfo
import io.uniflow.core.flow.data.UIState

open class PokemonDetailsState : UIState() {
    object PokemonIsLoading : PokemonDetailsState()
    data class PokemonRetrieved(val pokemon: PokemonInfo?) : PokemonDetailsState()
    data class PokemonRetrievedError(val message: String) : PokemonDetailsState()
}