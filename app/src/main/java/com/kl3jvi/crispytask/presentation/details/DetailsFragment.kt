package com.kl3jvi.crispytask.presentation.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.databinding.DetailsFragmentBinding
import com.kl3jvi.crispytask.utils.Constants.getColor
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private val passedArguments: DetailsFragmentArgs by navArgs()
    private val pokemon get() = passedArguments.pokemon
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonBind = pokemon

        viewModel.updatePokemonName(pokemon.name)
        onStates(viewModel) { state ->
            when (state) {
                is PokemonDetailsState -> showPokemon(state)
            }
        }
    }


    private fun showPokemon(state: PokemonDetailsState) {
        when (state) {
            is PokemonDetailsState.PokemonRetrieved->{
                binding.pokemonDetails = state.pokemon
            }
            is PokemonDetailsState.PokemonIsLoading->{

            }
            is PokemonDetailsState.PokemonRetrievedError->{
                Toast.makeText(requireContext(), "Error Retrieving Details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildPowerList(pInfo: PokemonInfoDto) {
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