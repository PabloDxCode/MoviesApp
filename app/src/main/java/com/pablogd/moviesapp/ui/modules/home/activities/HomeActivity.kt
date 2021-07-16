package com.pablogd.moviesapp.ui.modules.home.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.navigation.NavigationBarView
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ActivityHomeBinding
import com.pablogd.moviesapp.ui.base.BaseActivity
import com.pablogd.moviesapp.ui.modules.home.adapters.HomePagerAdapter
import com.pablogd.moviesapp.ui.modules.home.fragments.MoviesFragment
import com.pablogd.moviesapp.ui.modules.home.fragments.TvShowFragment
import com.pablogd.moviesapp.ui.modules.home.fragments.SettingsFragment

class HomeActivity : BaseActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    @SuppressLint("UseSupportActionBar")
    private fun initViews() = with(binding) {
        setSupportActionBar(toolbar)

        viewPagerHome.offscreenPageLimit = 3
        viewPagerHome.setPageTransformer(null)
        viewPagerHome.isUserInputEnabled = false
        viewPagerHome.adapter = HomePagerAdapter(
            supportFragmentManager, lifecycle,
            arrayListOf(MoviesFragment(), TvShowFragment(), SettingsFragment())
        )

        bnvHome.setOnItemSelectedListener(this@HomeActivity)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_movies -> binding.viewPagerHome.currentItem = 0
            R.id.menu_tv_shows -> binding.viewPagerHome.currentItem = 1
            R.id.menu_settings -> binding.viewPagerHome.currentItem = 2
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun setTitle(title: String) {
        binding.toolbar.title = if (title.isEmpty()) getString(R.string.app_name) else title
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }
}