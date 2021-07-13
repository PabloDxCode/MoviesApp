package com.pablogd.moviesapp

import com.pablogd.data.dataModule
import com.pablogd.domain.domainModule
import com.pablogd.moviesapp.ui.di.presentationModule
import org.koin.core.context.loadKoinModules

fun injectFeatures() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        arrayListOf(
            presentationModule,
            domainModule,
            dataModule
        )
    )
}