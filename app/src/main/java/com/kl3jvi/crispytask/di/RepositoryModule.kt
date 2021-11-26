package com.kl3jvi.crispytask.di


import com.kl3jvi.crispytask.data.network.PokemonApiClient
import com.kl3jvi.crispytask.data.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providePokemonRepository(pokemonApiClient: PokemonApiClient): PokemonRepository {
        return PokemonRepository(pokemonApiClient)
    }
}
