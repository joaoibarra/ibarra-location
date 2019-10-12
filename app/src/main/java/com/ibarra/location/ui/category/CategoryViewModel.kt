package com.ibarra.location.ui.category

import androidx.lifecycle.ViewModel
import com.ibarra.location.BuildConfig
import com.ibarra.location.data.remote.IbarraLocationApi
import io.reactivex.schedulers.Schedulers

class CategoryViewModel(private val ibarraLocationApi: IbarraLocationApi): ViewModel() {

    init {
        getNearbyLocationByCategory()
    }

    fun getNearbyLocationByCategory() {
        ibarraLocationApi.getNearbyPlaces()
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {

            })
    }
}