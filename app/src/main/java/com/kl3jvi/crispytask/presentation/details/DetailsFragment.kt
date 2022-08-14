package com.kl3jvi.crispytask.presentation.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.kl3jvi.crispytask.R
import com.kl3jvi.crispytask.databinding.DetailsFragmentBinding
import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.kl3jvi.crispytask.presentation.base.BindingFragment
import com.kl3jvi.crispytask.utils.Constants.getColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BindingFragment<DetailsFragmentBinding>(R.layout.details_fragment) {

    private val viewModel: DetailsViewModel by viewModels()
    private val passedArguments: DetailsFragmentArgs by navArgs()
    private val pokemon get() = passedArguments.pokemon


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding { pokemonBind = pokemon }

        viewModel.updatePokemonName(pokemon.name)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonUiState.collect { state ->
                    when (state) {
                        is PokemonUiState.Error -> Toast.makeText(
                            requireContext(),
                            "Error Occurred",
                            Toast.LENGTH_SHORT
                        ).show()
                        is PokemonUiState.Loading -> {}
                        is PokemonUiState.Success -> {
                            binding { pokemonDetails = state.data }
                            buildPowerList(state.data)
                        }
                    }
                }
            }
        }
    }

    private fun buildPowerList(pInfo: PokemonInfo) {
        val powerList = pInfo.types
        powerList.forEach {
            binding.chipGroup.removeAllViews()
            val chip = Chip(requireContext())
            chip.text = it.type.name
            chip.chipBackgroundColor = getColor(requireContext())
            chip.setTextColor(Color.WHITE)
            binding.chipGroup.addView(chip)
        }
    }
}