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
import com.pablogd.domain.models.Movie
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentMoviesBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.searchable.activities.SearchableActivity
import com.pablogd.moviesapp.ui.modules.home.adapters.MoviesAdapter
import com.pablogd.moviesapp.ui.modules.home.viewmodels.MoviesViewModel
import com.pablogd.moviesapp.ui.utils.PreferencesUtils
import com.pablogd.moviesapp.ui.utils.setUpAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment(R.layout.fragment_movies), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentMoviesBinding

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter(movieAction)
    }

    private val viewModel: MoviesViewModel by viewModel()

    private var pair: Pair<View, String>? = null

    private val movieAction: (Movie, View) -> Unit = { movie, view ->
        pair = Pair(view, DetailActivity.POSTER_TRANSITION_KEY)
        viewModel.saveItemClicked(movie)
    }

    companion object {
        private const val MOVIE_CATEGORY_KEY = "movie_category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        initViews()
        addObservers()
        viewModel.getMovies()

        binding.spCategories.onItemSelectedListener = this
    }

    private fun initViews() = with(binding) {
        spCategories.setUpAdapter(R.array.categories)
        loadCategory()
        rvMovies.adapter = adapter

        swipyRefreshLayout.setDistanceToTriggerSync(50)
        swipyRefreshLayout.setOnRefreshListener {
            swipyRefreshLayout.isRefreshing = false
            when (it) {
                //SwipyRefreshLayoutDirection.TOP -> getMovies(1)
                SwipyRefreshLayoutDirection.BOTTOM -> getMovies()
                else -> {
                    //Empty else
                }
            }
        }
    }

    private fun loadCategory() {
        val categoryItemSelected = PreferencesUtils(requireContext()).getInt(MOVIE_CATEGORY_KEY, 0)
        binding.spCategories.setSelection(categoryItemSelected, false)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getMovies(1)
        PreferencesUtils(requireContext()).save(MOVIE_CATEGORY_KEY, position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Empty method
    }

    private fun getMovies(page: Int = 0) {
        if (binding.spCategories.selectedItemPosition == 0) {
            viewModel.getPopularMovies(page)
        } else {
            viewModel.getTopRatedMovies(page)
        }
    }

    private fun addObservers() {
        viewModel.initScope()
        viewModel.model.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(model: MoviesViewModel.UiModel) {
        when (model) {
            is MoviesViewModel.UiModel.Loading -> showOrHideProgress(model.showProgress)
            is MoviesViewModel.UiModel.Content -> adapter.items = model.movies
            is MoviesViewModel.UiModel.Error -> baseView?.showError(model.error)
            is MoviesViewModel.UiModel.Navigation -> goDetail()
            is MoviesViewModel.UiModel.CategoryError -> restoreLastSelection()
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
        PreferencesUtils(requireContext()).save(MOVIE_CATEGORY_KEY, previousSelection)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_search -> {
            val intent = Intent(requireContext(), SearchableActivity::class.java)
            intent.putExtra(SearchableActivity.SECTION_KEY, SectionEnum.MOVIES.name)
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