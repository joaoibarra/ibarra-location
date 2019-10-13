package com.ibarra.location.ui.places

import androidx.paging.PagedList
import com.ibarra.location.data.db.entity.Place

class PlaceBoundaryCallback (
    private val viewModel: PlaceViewModel
) : PagedList.BoundaryCallback<Place>() {
    override fun onZeroItemsLoaded() {
    }

    override fun onItemAtEndLoaded(itemAtEnd: Place) {
        viewModel.getNearbyLocationByCategory()
    }
}