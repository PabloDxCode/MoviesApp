package com.pablogd.moviesapp.ui.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentsNavigationFactory(
    private val fragmentManager: FragmentManager,
    private val viewId: Int,
    bundle: Bundle? = null,
    private vararg val fragments: Fragment
) {

    companion object {

        const val CURRENT_ITEM_INDEX = "CURRENT_ITEM_INDEX"

    }

    private var currentFragment: Fragment? = null

    private var currentFragmentIndex = -1

    init {
        bundle?.let {
            currentFragmentIndex = it.getInt(CURRENT_ITEM_INDEX, -1)
        }
    }

    fun start(bundle: Bundle? = null) {
        if (currentFragmentIndex == -1) {
            currentFragmentIndex = bundle?.getInt(CURRENT_ITEM_INDEX, -1) ?: -1
        }
        if (fragments.isNotEmpty()) {
            changeFragment(if (currentFragmentIndex == -1) 0 else currentFragmentIndex)
        }
    }

    fun changeFragment(item: Int) {
        if (item < fragments.size) {
            replaceFragment(fragments[item], item)
        }
    }

    private fun replaceFragment(fragment: Fragment, item: Int) {
        if (currentFragment == null || currentFragment!!.javaClass.simpleName != fragment.javaClass.simpleName) {
            currentFragment?.let {
                fragmentManager.beginTransaction().remove(it).commitAllowingStateLoss()
            }
            fragmentManager.beginTransaction().replace(viewId, fragment).commitAllowingStateLoss()
            currentFragment = fragment
            currentFragmentIndex = item
        }
    }

    fun saveInstance(bundle: Bundle) {
        bundle.putInt(CURRENT_ITEM_INDEX, currentFragmentIndex)
    }

    fun onDestroy() {
        currentFragment = null
        currentFragmentIndex = -1
    }

}