package com.kl3jvi.crispytask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.crispytask.data.model.Pokemon
import com.kl3jvi.crispytask.databinding.ItemPokemonBinding
import com.kl3jvi.crispytask.presentation.main.MainFragment
import com.kl3jvi.crispytask.presentation.main.MainFragmentDirections


class PokemonAdapter(
    private val fragment: Fragment
) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(
    PokemonDiffCallback()
) {

    inner class PokemonViewHolder constructor(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                navigateToDetails(view)
            }
        }

        private fun navigateToDetails(view: View) {
            if (fragment is MainFragment) {
                val direction = MainFragmentDirections.actionMainFragmentToDetailsFragment()
                view.findNavController().navigate(direction)
            }
        }

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
