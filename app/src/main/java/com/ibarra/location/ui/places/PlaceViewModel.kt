package com.ibarra.location.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ibarra.location.data.db.dao.PlaceDao
import com.ibarra.location.data.db.entity.Place
import com.ibarra.location.data.remote.IbarraLocationApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PlaceViewModel(private val ibarraLocationApi: IbarraLocationApi,
                     private val dao: PlaceDao): ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    var items: LiveData<PagedList<Place>>? = null
    var factory: DataSource.Factory<Int, Place>? = null
    var latitude: Double? = null
    var longitude: Double? = null
    val error = MutableLiveData<Boolean>()
    val progress= MutableLiveData<Boolean>()
    var pageToken: String? = ""
    var category = "cafe"

    init {
        initPagedList()
    }

    private fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun getNearbyLocationByCategory() {
        if(latitude!=null && longitude!=null) {
            addToDisposable(
                ibarraLocationApi.getNearbyPlaces(
                    category,
                    pageToken,
                    latitude.toString().plus(",").plus(longitude.toString())
                )
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { showProgress() }
                    .doOnSuccess{ hideProgress() }
                    .doOnError { hideProgress() }
                    .delay(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        error.postValue(false)
                        pageToken = it.nextPageToken
                        dao.insertAll(Place.toList(it.results, category))
                    }, {
                        error.postValue(true)
                    })
            )
        }
    }

    private fun initPagedList() {
        factory = dao.findPlaceByType(category)
        factory?.let {
            val pagedListBuilder: LivePagedListBuilder<Int, Place>  = LivePagedListBuilder<Int, Place>(it,
                20)
            items = pagedListBuilder
                .setBoundaryCallback(PlaceBoundaryCallback(this))
                .build()
        }
    }

    private fun showProgress() {
        if(items?.getValue().isNullOrEmpty()) {
            progress.postValue(true)
        }
    }

    private fun hideProgress() {
        progress.postValue(false)
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
        getNearbyLocationByCategory()
    }

    fun onTabItemClick(type: String) {
        items?.value?.dataSource?.invalidate()
        this.category = type.toLowerCase()
        this.pageToken = ""
        initPagedList()
        getNearbyLocationByCategory()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}