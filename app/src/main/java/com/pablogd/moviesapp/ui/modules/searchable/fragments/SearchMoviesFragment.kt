package com.pablogd.moviesapp.ui.modules.searchable.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.widget.SearchView
import com.pablogd.domain.models.Movie
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentSearchMoviesBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.searchable.adapters.SearchMoviesAdapter
import com.pablogd.moviesapp.ui.modules.searchable.viewmodels.SearchMoviesViewModel
import com.pablogd.moviesapp.ui.utils.notNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMoviesFragment : BaseFragment(R.layout.fragment_search_movies),
    SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentSearchMoviesBinding

    private val adapter: SearchMoviesAdapter by lazy {
        SearchMoviesAdapter(items, listener)
    }

    private val items = mutableListOf<Movie>()

    private val listener: (Movie, View) -> Unit = { movie, view ->
        pair = Pair(view, DetailActivity.POSTER_TRANSITION_KEY)
        viewModel.saveItemClicked(movie)
    }

    private var pair: Pair<View, String>? = null

    private val viewModel: SearchMoviesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchMoviesBinding.bind(view)

        initViews()
        addObservers()
    }

    private fun initViews() = with(binding) {
        rvItems.adapter = adapter
        svItems.setOnQueryTextListener(this@SearchMoviesFragment)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.getSearch(query.notNull())
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(items.isNotEmpty()) {
            items.clear()
            adapter.notifyDataSetChanged()
            binding.rvItems.visibility = View.GONE
        }
        return false
    }

    private fun addObservers() {
        viewModel.initScope()
        viewModel.model.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(model: SearchMoviesViewModel.UiModel) {
        when (model) {
            is SearchMoviesViewModel.UiModel.Loading -> showOrHideProgress(model.showProgress)
            is SearchMoviesViewModel.UiModel.Content -> configList(model.movies)
            is SearchMoviesViewModel.UiModel.Error -> baseView?.showError(model.error)
            is SearchMoviesViewModel.UiModel.Navigation -> goDetail()
        }
    }

    private fun configList(movies: List<Movie>){
        items.clear()
        if(movies.isEmpty()){
            binding.rvItems.visibility = View.GONE
        } else {
            binding.rvItems.visibility = View.VISIBLE
            items.addAll(movies.toMutableList())
            adapter.notifyDataSetChanged()
        }
    }

    private fun goDetail() {
        pair?.let {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            goDetail(intent, it)
        }
    }

}