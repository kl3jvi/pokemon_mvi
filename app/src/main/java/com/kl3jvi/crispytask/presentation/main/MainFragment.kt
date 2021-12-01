package com.kl3jvi.crispytask.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kl3jvi.crispytask.data.model.PokemonDto
import com.kl3jvi.crispytask.data.model.PokemonResponseDto
import com.kl3jvi.crispytask.databinding.MainFragmentBinding
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
                is PokemonResponseDto -> {
                    val pokemonList = state.results
                    showPokemons(pokemonList)
                }
                else -> {
                    pokemonsAreLoading()
                }
            }
        }
    }

    private fun showPokemons(pokemonDtoList: List<PokemonDto>) {
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