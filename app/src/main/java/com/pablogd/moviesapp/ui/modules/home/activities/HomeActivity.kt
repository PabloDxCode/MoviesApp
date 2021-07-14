package com.pablogd.moviesapp.ui.modules.home.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ActivityHomeBinding
import com.pablogd.moviesapp.ui.base.BaseActivity
import com.pablogd.moviesapp.ui.modules.home.fragments.MoviesFragment
import com.pablogd.moviesapp.ui.modules.home.fragments.TvShowFragment
import com.pablogd.moviesapp.ui.modules.home.fragments.SettingsFragment
import com.pablogd.moviesapp.ui.utils.FragmentsNavigationFactory

class HomeActivity : BaseActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var mFragmentsNavigationFactory: FragmentsNavigationFactory

    private val moviesFragment = MoviesFragment()

    private val tvShowFragment = TvShowFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFragmentsNavigationFactory = FragmentsNavigationFactory(supportFragmentManager, R.id.container,
            savedInstanceState, moviesFragment, tvShowFragment, SettingsFragment())
        mFragmentsNavigationFactory.start(intent?.extras)
        initViews()
    }

    @SuppressLint("UseSupportActionBar")
    private fun initViews() = with(binding) {
        bnvHome.setOnItemSelectedListener(this@HomeActivity)
        setSupportActionBar(toolbar)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_movies -> mFragmentsNavigationFactory.changeFragment(0)
            R.id.menu_tv_shows -> mFragmentsNavigationFactory.changeFragment(1)
            R.id.menu_settings -> mFragmentsNavigationFactory.changeFragment(2)
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mFragmentsNavigationFactory.saveInstance(outState)
        super.onSaveInstanceState(outState)
    }

    override fun setTitle(title: String) {
        binding.toolbar.title = title
    }

}