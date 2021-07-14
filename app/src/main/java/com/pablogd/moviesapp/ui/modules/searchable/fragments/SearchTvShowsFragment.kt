package com.pablogd.moviesapp.ui.modules.searchable.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.widget.SearchView
import com.pablogd.domain.models.TvShow
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentSearchableBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.searchable.adapters.SearchTvShowsAdapter
import com.pablogd.moviesapp.ui.modules.searchable.viewmodels.SearchTvShowsViewModel
import com.pablogd.moviesapp.ui.utils.notNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchTvShowsFragment : BaseFragment(R.layout.fragment_searchable),
    SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentSearchableBinding

    private val adapter: SearchTvShowsAdapter by lazy {
        SearchTvShowsAdapter(items, listener)
    }

    private val items = mutableListOf<TvShow>()

    private val listener: (TvShow, View) -> Unit = { tvShow, view ->
        pair = Pair(view, DetailActivity.POSTER_TRANSITION_KEY)
        viewModel.saveItemClicked(tvShow)
    }

    private var pair: Pair<View, String>? = null

    private val viewModel: SearchTvShowsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchableBinding.bind(view)

        initViews()
        addObservers()
    }

    private fun initViews() = with(binding) {
        rvItems.adapter = adapter
        svItems.setOnQueryTextListener(this@SearchTvShowsFragment)
        tvEmptyList.text = requireActivity().getString(R.string.search_favorite_tv_show)
        if (items.isEmpty()) {
            tvEmptyList.visibility = View.VISIBLE
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.getSearch(query.notNull())
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (items.isNotEmpty()) {
            items.clear()
            adapter.notifyDataSetChanged()
            binding.rvItems.visibility = View.GONE
            binding.tvEmptyList.visibility = View.VISIBLE
        }
        return false
    }

    private fun addObservers() {
        viewModel.initScope()
        viewModel.model.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(model: SearchTvShowsViewModel.UiModel) {
        when (model) {
            is SearchTvShowsViewModel.UiModel.Loading -> showOrHideProgress(model.showProgress)
            is SearchTvShowsViewModel.UiModel.Content -> configList(model.movies)
            is SearchTvShowsViewModel.UiModel.Error -> {
                // Empty else
            }
            is SearchTvShowsViewModel.UiModel.Navigation -> goDetail()
        }
    }

    private fun configList(tvShow: List<TvShow>) {
        items.clear()
        if (tvShow.isEmpty()) {
            binding.rvItems.visibility = View.GONE
            binding.tvEmptyList.visibility = View.VISIBLE
        } else {
            binding.rvItems.visibility = View.VISIBLE
            binding.tvEmptyList.visibility = View.GONE
            items.addAll(tvShow.toMutableList())
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