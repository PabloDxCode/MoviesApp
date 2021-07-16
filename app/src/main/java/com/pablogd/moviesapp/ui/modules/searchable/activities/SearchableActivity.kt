package com.pablogd.moviesapp.ui.modules.searchable.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pablogd.domain.enums.SectionEnum
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ActivitySearchableBinding
import com.pablogd.moviesapp.ui.base.BaseActivity
import com.pablogd.moviesapp.ui.modules.searchable.fragments.SearchMoviesFragment
import com.pablogd.moviesapp.ui.modules.searchable.fragments.SearchTvShowsFragment

class SearchableActivity: BaseActivity() {

    private lateinit var binding: ActivitySearchableBinding

    companion object {
        const val SECTION_KEY = "sectionKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sectionSelected = intent?.getStringExtra(SECTION_KEY)
        if(sectionSelected == SectionEnum.MOVIES.name){
            loadFragment(SearchMoviesFragment())
        } else {
            loadFragment(SearchTvShowsFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.searchableContainer, fragment)
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

}