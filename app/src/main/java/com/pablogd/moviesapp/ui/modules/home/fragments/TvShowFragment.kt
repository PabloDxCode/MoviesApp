package com.pablogd.moviesapp.ui.modules.home.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import com.pablogd.domain.enums.SectionEnum
import com.pablogd.domain.models.TvShow
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentTvShowsBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.home.adapters.TvShowsAdapter
import com.pablogd.moviesapp.ui.modules.home.viewmodels.TvShowsViewModel
import com.pablogd.moviesapp.ui.modules.searchable.activities.SearchableActivity
import com.pablogd.moviesapp.ui.utils.PreferencesUtils
import com.pablogd.moviesapp.ui.utils.setUpAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : BaseFragment(R.layout.fragment_tv_shows),
    AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentTvShowsBinding

    private val adapter: TvShowsAdapter by lazy {
        TvShowsAdapter(movieAction)
    }

    private val viewModel: TvShowsViewModel by viewModel()

    private var pair: Pair<View, String>? = null

    private val movieAction: (TvShow, View) -> Unit = { tvShow, view ->
        pair = Pair(view, DetailActivity.POSTER_TRANSITION_KEY)
        viewModel.saveItemClicked(tvShow)
    }

    companion object {
        private const val TV_SHOW_CATEGORY_KEY = "tv_show_category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTvShowsBinding.bind(view)
        initViews()
        addObservers()
        viewModel.getTvShows()

        binding.spCategories.onItemSelectedListener = this
    }

    private fun initViews() = with(binding) {
        spCategories.setUpAdapter(R.array.categories)
        loadCategory()
        rvTvShows.adapter = adapter

        swipyRefreshLayout.setDistanceToTriggerSync(50)
        swipyRefreshLayout.setOnRefreshListener {
            swipyRefreshLayout.isRefreshing = false
            when (it) {
                //SwipyRefreshLayoutDirection.TOP -> getTvShows(1)
                SwipyRefreshLayoutDirection.BOTTOM -> getTvShows()
                else -> {
                    //Empty else
                }
            }
        }
    }

    private fun loadCategory() {
        val categorySelected = PreferencesUtils(requireContext()).getInt(TV_SHOW_CATEGORY_KEY, 0)
        binding.spCategories.setSelection(categorySelected, false)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getTvShows(1)
        PreferencesUtils(requireContext()).save(TV_SHOW_CATEGORY_KEY, position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Empty method
    }

    private fun getTvShows(page: Int = 0) {
        if (binding.spCategories.selectedItemPosition == 0) {
            viewModel.getPopularTvShows(page)
        } else {
            viewModel.getTopRatedTvShows(page)
        }
    }

    private fun addObservers() {
        viewModel.initScope()
        viewModel.model.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(model: TvShowsViewModel.UiModel) {
        when (model) {
            is TvShowsViewModel.UiModel.Loading -> showOrHideProgress(model.showProgress)
            is TvShowsViewModel.UiModel.Content -> adapter.items = model.tvShows
            is TvShowsViewModel.UiModel.Error -> baseView?.showError(model.error)
            is TvShowsViewModel.UiModel.Navigation -> goDetail()
            is TvShowsViewModel.UiModel.CategoryError -> restoreLastSelection()
        }
    }

    private fun goDetail() {
        pair?.let {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            goDetail(intent, it)
        }
    }

    private fun restoreLastSelection() {
        val currentSelection = binding.spCategories.selectedItemPosition
        val previousSelection = if (currentSelection == 0) 1 else 0
        binding.spCategories.onItemSelectedListener = null
        binding.spCategories.setSelection(previousSelection, false)
        binding.spCategories.onItemSelectedListener = this
        PreferencesUtils(requireContext()).save(TV_SHOW_CATEGORY_KEY, previousSelection)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_search -> {
            val intent = Intent(requireContext(), SearchableActivity::class.java)
            intent.putExtra(SearchableActivity.SECTION_KEY, SectionEnum.TV_SHOWS.name)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pair = null
    }

}