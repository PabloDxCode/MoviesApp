package com.pablogd.moviesapp.ui.modules.home.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.pablogd.domain.models.Movie
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentMoviesBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.home.adapters.MoviesAdapter
import com.pablogd.moviesapp.ui.utils.setUpAdapter

class MoviesFragment: BaseFragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter(movieAction)
    }

    private val movieAction: (Movie, View) ->Unit = { movie, view ->
        val intent = Intent(requireContext(), DetailActivity::class.java)
        val pair = Pair(view, "poster")
        goDetail(intent, pair)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        initViews()
    }

    private fun initViews() = with(binding) {
        spCategories.setUpAdapter(R.array.categories)
        rvMovies.adapter = adapter
        adapter.items = getMocks()
    }

    private fun getMocks(): List<Movie> = arrayListOf(
        Movie(1, "Title 1", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false),
        Movie(2, "Title 2", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false),
        Movie(3, "Title 3", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false),
        Movie(4, "Title 4", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false),
        Movie(5, "Title 5", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false),
        Movie(6, "Title 6", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false),
        Movie(7, "Title 7", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false),
        Movie(8, "Title 8", "Description", "12-10-2021", "/tzp6VzED2TBc02bkYoYnQa6r2OK.jpg", "", "English", "Title", 8.4, 400.0, false)
    )

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_search -> {

            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}