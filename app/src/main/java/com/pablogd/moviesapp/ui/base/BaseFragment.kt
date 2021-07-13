package com.pablogd.moviesapp.ui.base

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    protected var baseView: BaseView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseView) {
            baseView = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (baseView == null) {
            baseView = null
        }
    }

    protected fun goDetail(intent: Intent, pair: Pair<View, String>) {
        activity?.let {
            val options = ActivityOptions.makeSceneTransitionAnimation(it, pair)
            it.startActivity(intent, options.toBundle())
        }
    }

}