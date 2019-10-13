package com.ibarra.location.ui.places

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ibarra.location.BuildConfig
import com.ibarra.location.R
import com.ibarra.location.data.db.entity.Place
import com.ibarra.location.databinding.ItemPlaceBinding
import com.ibarra.location.ui.binding.BindingViewHolder

class PlaceAdapter (private val vm: PlaceViewModel) :
    PagedListAdapter<Place, PlaceAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_place,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { place ->
            holder.binding?.apply{
                 item = place
                viewModel = vm
                loadImage(ivNewsImage, place.photo)
            }
        }
    }

    private fun loadImage(view: ImageView, photoreference: String?){
        val url = String.format(BuildConfig.IMAGE_URL, photoreference, BuildConfig.API_KEY)
        Glide.with(view.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    class CategoryViewHolder(view: View) : BindingViewHolder<ItemPlaceBinding>(view)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Place>() {
            override fun areItemsTheSame(oldItem: Place, newItem: Place) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Place, newItem: Place) = oldItem == newItem
        }
    }
}