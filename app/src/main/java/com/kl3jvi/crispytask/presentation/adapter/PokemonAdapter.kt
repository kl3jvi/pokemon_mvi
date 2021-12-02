package com.kl3jvi.crispytask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.crispytask.databinding.ItemPokemonBinding
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.presentation.main.MainFragmentDirections


class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(
    PokemonDiffCallback()
) {

    inner class PokemonViewHolder constructor(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.pokemonObj?.let { pokemonDetails ->
                    navigateToDetails(pokemonDetails, view)
                }
            }
        }

        private fun navigateToDetails(pokemon: Pokemon, view: View) {
            val direction =
                MainFragmentDirections.actionMainFragmentToDetailsFragment(pokemon)
            view.findNavController().navigate(direction)
        }

        fun bindPokemon(pokemon: Pokemon) {
            binding.apply {
                pokemonObj = pokemon
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding: ItemPokemonBinding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) =
        holder.bindPokemon(getItem(position))
}

private class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(
        oldItem: Pokemon,
        newItem: Pokemon
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: Pokemon,
        newItem: Pokemon
    ): Boolean {
        return oldItem.name == newItem.name
    }
}
