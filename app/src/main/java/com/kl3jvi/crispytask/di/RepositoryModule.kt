package com.kl3jvi.crispytask.di


import com.kl3jvi.crispytask.data.network.PokemonApiClient
import com.kl3jvi.crispytask.data.persistence.PokemonDao
import com.kl3jvi.crispytask.data.persistence.PokemonInfoDao
import com.kl3jvi.crispytask.data.repository.PokemonRepositoryImpl
import com.kl3jvi.crispytask.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        pokemonApiClient: PokemonApiClient,
        pokemonDao: PokemonDao,
        pokemonInfoDao: PokemonInfoDao,
        ioDispatcher: CoroutineDispatcher
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApiClient, pokemonDao, pokemonInfoDao, ioDispatcher)
    }

}
