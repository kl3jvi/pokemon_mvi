package com.kl3jvi.crispytask.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kl3jvi.crispytask.data.model.PokemonInfo
import com.kl3jvi.crispytask.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private val passedArguments: DetailsFragmentArgs by navArgs()
    private val pokemonName get() = passedArguments.pokemonName
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
        viewModel.updatePokemonName(pokemonName)
        onStates(viewModel) { state ->
            when (state) {
                is PokemonInfo -> {
                    binding.pokemonDetails = state
                }
                else -> {
                    Log.e("Idk", state.toString())
                }
            }
        }
    }
}