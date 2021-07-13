package com.pablogd.moviesapp

import org.koin.core.context.loadKoinModules

fun injectFeatures() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        arrayListOf(

        )
    )
}