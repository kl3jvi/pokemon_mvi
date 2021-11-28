package com.kl3jvi.crispytask.presentation.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kl3jvi.crispytask.data.model.PokemonInfo
import com.kl3jvi.crispytask.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
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
        viewModel.updatePokemonName("spearow")
        onStates(viewModel) { state ->
            when (state) {
                is PokemonInfo -> {
                    Log.e("Pokemon Details",state.name)
                    binding.message.append(" ${state.experience}")
                }
                else-> {
                    Log.e("Idk",state.toString())
                }
            }
        }
    }
}