package com.pablogd.moviesapp.ui.base

import android.widget.Toast
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

    override fun showError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_SHORT).show()
    }

}