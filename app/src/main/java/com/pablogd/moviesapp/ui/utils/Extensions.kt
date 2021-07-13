package com.pablogd.moviesapp.ui.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
    initialValue: List<T>,
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
    Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this@basicDiffUtil)
    }

fun View?.addAnimation(){
    this?.let { view ->
        val centerX = 0
        val centerY = 0
        val startRadius = 0f
        val endRadius = view.width.coerceAtLeast(view.height).toFloat()
        val animation =
            ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius)
        view.visibility = View.VISIBLE
        animation.start()
    }
}

//Strings

fun String?.notNull(): String = this ?: ""


// Edittext

fun EditText?.safeText(): String = this?.text?.toString() ?: ""


//Activity

fun Activity?.openKeyboard(editText: EditText){
    this?.let { activity ->
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.SHOW_IMPLICIT)
        editText.requestFocus()
        editText.setSelection(editText.text.length)
    }
}

fun Activity?.closeKeyboard(){
    this?.let { activity ->
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

// Spinner

fun Spinner.setUpAdapter(array: Int){
    val spAdapter = ArrayAdapter.createFromResource(context, array, android.R.layout.simple_spinner_item)
    spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    adapter = spAdapter
}

