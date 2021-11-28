package com.kl3jvi.crispytask.di


import com.kl3jvi.crispytask.data.network.PokemonApiClient
import com.kl3jvi.crispytask.data.repository.DetailsRepository
import com.kl3jvi.crispytask.data.repository.MainRepository
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
    fun provideMainRepository(pokemonApiClient: PokemonApiClient): MainRepository {
        return MainRepository(pokemonApiClient)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailsRepository(pokemonApiClient: PokemonApiClient): DetailsRepository {
        return DetailsRepository(pokemonApiClient)
    }
}
