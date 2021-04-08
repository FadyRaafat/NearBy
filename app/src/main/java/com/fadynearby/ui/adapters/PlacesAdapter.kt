package com.fadynearby.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.fadynearby.R
import com.fadynearby.api.models.FoursquareResponse
import com.fadynearby.databinding.ItemPlaceBinding
import com.fadynearby.ui.common.DataBoundListAdapter
import com.fadynearby.ui.utils.Utils.extractImageUrl
import com.fadynearby.ui.utils.Utils.extractVenueAddress
import com.fadynearby.viewmodels.PlacesViewModel

class PlacesAdapter(var placesViewModel: PlacesViewModel) :
    DataBoundListAdapter<FoursquareResponse.Response.Group.Item, ItemPlaceBinding>(
        diffCallback = object : DiffUtil.ItemCallback<FoursquareResponse.Response.Group.Item>() {
            override fun areContentsTheSame(
                oldItem: FoursquareResponse.Response.Group.Item,
                newItem: FoursquareResponse.Response.Group.Item
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: FoursquareResponse.Response.Group.Item,
                newItem: FoursquareResponse.Response.Group.Item
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {


    override fun createBinding(parent: ViewGroup): ItemPlaceBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_place,
            parent,
            false
        )
    }

    override fun bind(
        binding: ItemPlaceBinding,
        item: FoursquareResponse.Response.Group.Item,
        position: Int,
        adapterPosition: Int
    ) {
        binding.item = item
        binding.placeAddressTV.text = extractVenueAddress(item)
        getVenuePhotos(item, binding)
    }

    private fun getVenuePhotos(
        item: FoursquareResponse.Response.Group.Item,
        binding: ItemPlaceBinding
    ) {
        placesViewModel.getVenuePhotos(item.venue?.id) { success, response ->
            if (success) {
                binding.imageUrl = extractImageUrl(response)
            }
        }

    }

}