package com.ibarra.location.ui.places

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.ibarra.location.data.db.entity.Place

@BindingAdapter(value = ["places", "viewModel"])
fun setPlaces(view: RecyclerView, items: PagedList<Place>?, vm: PlaceViewModel) {
    view.adapter?.run {
        if (this is PlaceAdapter) this.submitList(items)
    } ?: run {
        PlaceAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}