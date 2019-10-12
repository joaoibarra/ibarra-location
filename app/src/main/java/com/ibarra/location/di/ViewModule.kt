package com.ibarra.location.di

import com.ibarra.location.ui.category.CategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { CategoryViewModel(get()) }
}