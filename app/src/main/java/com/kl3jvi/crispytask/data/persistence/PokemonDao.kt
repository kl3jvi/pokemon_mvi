package com.kl3jvi.crispytask.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kl3jvi.crispytask.data.model.PokemonDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonDto>)

    @Query("SELECT * FROM PokemonDto")
    suspend fun getPokemonList(): List<PokemonDto>

}