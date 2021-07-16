package com.pablogd.moviesapp.ui.modules.home.fragments

import android.os.Bundle
import android.view.View
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentSettingsBinding
import com.pablogd.moviesapp.ui.base.BaseFragment

class SettingsFragment: BaseFragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        initViews()
    }

    private fun initViews() = with(binding) {
        //Empty method
    }

}