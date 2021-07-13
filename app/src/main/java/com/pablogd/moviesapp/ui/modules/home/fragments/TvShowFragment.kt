package com.pablogd.moviesapp.ui.modules.home.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.pablogd.domain.models.TvShow
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentTvShowsBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.home.adapters.TvShowsAdapter
import com.pablogd.moviesapp.ui.utils.setUpAdapter

class TvShowFragment: BaseFragment(R.layout.fragment_tv_shows) {

    private lateinit var binding: FragmentTvShowsBinding

    private val adapter: TvShowsAdapter by lazy {
        TvShowsAdapter(movieAction)
    }

    private val movieAction: (TvShow, View) ->Unit = { tvShow, view ->
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
        binding = FragmentTvShowsBinding.bind(view)
        initViews()
    }

    private fun initViews() = with(binding) {
        spCategories.setUpAdapter(R.array.categories)
        rvTvShows.adapter = adapter
        adapter.items = getMocks()
    }

    private fun getMocks(): List<TvShow> = arrayListOf(
        TvShow(1, "Title 1", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(2, "Title 2", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(3, "Title 3", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(4, "Title 4", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(5, "Title 5", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(6, "Title 6", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(7, "Title 7", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(8, "Title 8", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(9, "Title 9", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200),
        TvShow(10, "Title 10", "Description","/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg", "", "12-10-2021", arrayListOf(), arrayListOf(), "English", "Title", 9.0, 100.0, 200)
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