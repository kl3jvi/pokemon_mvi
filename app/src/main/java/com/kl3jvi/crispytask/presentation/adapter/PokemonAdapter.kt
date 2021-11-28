package com.kl3jvi.crispytask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.crispytask.databinding.ItemPokemonBinding
import com.kl3jvi.crispytask.domain.model.Pokemon


class PokemonAdapter(
    private val fragment: Fragment
//    val action: (items: MutableList<Pokemon>, changed: Pokemon, checked: Boolean) -> Unit
) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(DiffCallback()) {

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
        holder.bindPokemon(getItem(position))

    override fun getItemCount(): Int {
        return currentList.size
    }
}

/**
 * Increase recycler view performance using implementation of diffutil
 * algorithm for recyclerview
 */
private class DiffCallback : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) =
        oldItem == newItem
}