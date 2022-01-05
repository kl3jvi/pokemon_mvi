package com.kl3jvi.crispytask.presentation.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kl3jvi.crispytask.R
import com.kl3jvi.crispytask.databinding.MainFragmentBinding
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.presentation.adapter.PokemonAdapter
import com.kl3jvi.crispytask.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates

@AndroidEntryPoint
class MainFragment : BindingFragment<MainFragmentBinding>(R.layout.main_fragment) {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding {
            vm = viewModel
        }


        onStates(viewModel) { state ->
            when (state) {
                is PokemonListState -> showPokemons(state)
            }
        }
    }

    private fun showPokemons(state: PokemonListState) {
        when (state) {
            is PokemonListState.PokemonsRetrieved -> displayPokemonList(state.pokemons)

            is PokemonListState.PokemonsAreLoading -> {
                pokemonsAreLoading()
            }
            is PokemonListState.PokemonsRetrievedError -> {
                showToast(state.error)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun displayPokemonList(pokemonDtoList: List<Pokemon>) {
        binding {
            adapter = PokemonAdapter()
            recyclerView.adapter = adapter
            progressBar.visibility = GONE
            recyclerView.visibility = VISIBLE
            adapter.submitList(pokemonDtoList)
        }
    }

    private fun pokemonsAreLoading() {
        binding {
            recyclerView.visibility = GONE
            progressBar.visibility = VISIBLE
        }
    }
}