package com.ibarra.location.di

import com.ibarra.location.ui.places.PlaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { PlaceViewModel(get(), get()) }
}