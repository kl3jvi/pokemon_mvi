package com.kl3jvi.crispytask.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kl3jvi.crispytask.databinding.MainFragmentBinding
import com.kl3jvi.crispytask.data.model.PokemonResponse
import com.kl3jvi.crispytask.presentation.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onStates(viewModel) { state ->
            Log.e("State:----", state.toString())
            when (state) {
                is PokemonResponse -> {
                    val adapter = PokemonAdapter(this,state.results)
                    binding.recyclerView.adapter = adapter
                    state.results.forEach {
                        binding.message.append("${it.name} \n")
                    }
                }
                else -> {
                    Toast.makeText(requireContext(), "Loading pokemons", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}