package com.pablogd.moviesapp.ui.base

interface BaseView {

    fun showProgress()

    fun hideProgress()

    fun setTitle(title: String)

    fun showError(message: String)

}