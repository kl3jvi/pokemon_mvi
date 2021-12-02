package com.kl3jvi.crispytask.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.crispytask.presentation.main.MainViewModel
import com.kl3jvi.crispytask.utils.RecyclerViewPaginator

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("paginationPokemonList")
    fun paginationPokemonList(view: RecyclerView, viewModel: MainViewModel) {
        RecyclerViewPaginator(
            recyclerView = view,
            isLoading = { viewModel.isLoading },
            loadMore = { viewModel.fetchNextPokemonList() },
            onLast = { false }
        ).run {
            threshold = 8
        }
    }
}