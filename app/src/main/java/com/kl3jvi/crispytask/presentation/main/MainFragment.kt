package com.kl3jvi.crispytask.presentation.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kl3jvi.crispytask.R
import com.kl3jvi.crispytask.databinding.MainFragmentBinding
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.presentation.adapter.PokemonAdapter
import com.kl3jvi.crispytask.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BindingFragment<MainFragmentBinding>(R.layout.main_fragment) {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding { vm = viewModel }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonUiState.collect { state ->
                    when (state) {
                        PokemonsUiState.PokemonsAreLoading -> pokemonsAreLoading()
                        is PokemonsUiState.PokemonsRetrieved -> displayPokemonList(state.pokemons)
                        is PokemonsUiState.PokemonsRetrievedError -> showToast(
                            state.error?.message ?: "Error!"
                        )
                    }
                }
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