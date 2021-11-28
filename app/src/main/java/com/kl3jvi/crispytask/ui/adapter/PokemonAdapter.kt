package com.kl3jvi.crispytask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.crispytask.data.model.Pokemon
import com.kl3jvi.crispytask.databinding.ItemPokemonBinding
import com.kl3jvi.crispytask.ui.main.MainFragmentDirections


class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(
    PokemonDiffCallback()
) {

    inner class PokemonViewHolder constructor(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.pokemonInfo?.name?.let { nameField->
                    navigateToDetails(nameField, view)
                }
            }
        }

        private fun navigateToDetails(pokemonName: String, view: View) {
            val direction = MainFragmentDirections.actionMainFragmentToDetailsFragment(pokemonName)
            view.findNavController().navigate(direction)
        }

        fun bindPokemon(pokemon: Pokemon) {
            binding.pokemonInfo = pokemon
            binding.executePendingBindings()
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
