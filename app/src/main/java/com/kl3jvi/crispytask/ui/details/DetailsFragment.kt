package com.kl3jvi.crispytask.ui.details

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.kl3jvi.crispytask.data.model.PokemonInfo
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
        viewModel.updatePokemonName(pokemon.name)
        onStates(viewModel) { state ->
            when (state) {
                is PokemonInfo -> {
                    binding.apply {
                        pokemonDetails = state
                        pokemonBind = pokemon
                    }
                    buildPowerList(state)
                }
                else -> {
                    Log.e("Idk", state.toString())
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