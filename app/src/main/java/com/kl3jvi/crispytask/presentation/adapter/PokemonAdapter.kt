package com.kl3jvi.crispytask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.crispytask.data.model.Pokemon
import com.kl3jvi.crispytask.databinding.ItemPokemonBinding


class PokemonAdapter(
    private val fragment: Fragment,
    private val list: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder constructor(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindPokemon(pokemon: Pokemon) {
            binding.pokemonInfo = pokemon
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding: ItemPokemonBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) =
        holder.bindPokemon(list[position])

    override fun getItemCount(): Int = list.size
}
