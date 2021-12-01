package com.kl3jvi.crispytask.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kl3jvi.crispytask.databinding.MainFragmentBinding
import com.kl3jvi.crispytask.domain.model.Pokemon
import com.kl3jvi.crispytask.presentation.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PokemonAdapter()

        onStates(viewModel) { state ->
            when (state) {
                is PokemonListState -> showPokemons(state)
            }
        }
    }

    private fun showPokemons(state: PokemonListState) {
        when (state) {
            is PokemonListState.PokemonsRetrieved -> {
                displayPokemonList(state.pokemons)
            }
            is PokemonListState.PokemonsAreLoading -> {
                pokemonsAreLoading()
            }
            is PokemonListState.PokemonsRetrievedError -> {
                showToast(state.message)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun displayPokemonList(pokemonDtoList: List<Pokemon>) {
        binding.apply {
            recyclerView.adapter = adapter
            progressBar.visibility = GONE
            adapter.submitList(pokemonDtoList)
        }
    }

    private fun pokemonsAreLoading() {
        binding.apply {
            recyclerView.visibility = GONE
            progressBar.visibility = VISIBLE
        }
    }
}