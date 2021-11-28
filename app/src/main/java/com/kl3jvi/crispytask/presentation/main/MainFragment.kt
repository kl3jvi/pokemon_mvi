package com.kl3jvi.crispytask.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kl3jvi.crispytask.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates
import io.uniflow.core.flow.data.UIState

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
            when (state) {
                is UIState.Success -> {

                }
                is UIState.Empty -> {

                }
                is UIState.Loading -> {

                }
            }
        }
    }
}