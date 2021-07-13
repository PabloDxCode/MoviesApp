package com.pablogd.moviesapp.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity(), BaseView {

    override fun showProgress() {
        // Empty method
    }

    override fun hideProgress() {
        // Empty method
    }

    override fun setTitle(title: String) {
        // Empty method
    }

}