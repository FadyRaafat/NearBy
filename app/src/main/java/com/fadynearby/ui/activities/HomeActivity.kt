package com.fadynearby.ui.activities

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fadynearby.R
import com.fadynearby.api.models.FoursquareResponse
import com.fadynearby.databinding.ActivityHomeBinding
import com.fadynearby.ui.adapters.PlacesAdapter
import com.fadynearby.ui.utils.NetworkState
import com.fadynearby.ui.utils.Utils.ONETIME
import com.fadynearby.ui.utils.Utils.REALTIME
import com.fadynearby.viewmodels.PlacesViewModel


class HomeActivity : AppCompatActivity() {

    var locationManager: LocationManager? = null
    private val placesViewModel: PlacesViewModel by viewModels()
    lateinit var binding: ActivityHomeBinding
    var adapter: PlacesAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        adapter = PlacesAdapter(placesViewModel)
        placesViewModel.setupLocationManager(locationManager)
        binding.placesviewmodel = placesViewModel
        initToggleRealTime()

        placesViewModel.networkStatusMutableLiveData.observe(this, {
            when (it) {
                NetworkState.LOADING -> {
                    binding.emptyResponseTV.visibility = View.GONE
                    binding.errorTV.visibility = View.GONE
                }
                NetworkState.EMPTY_RESPONSE -> {
                    binding.emptyResponseTV.visibility = View.VISIBLE
                    adapter?.submitList(null)
                    adapter?.notifyDataSetChanged()
                }
                NetworkState.FAILED -> {
                    binding.errorTV.visibility = View.VISIBLE
                }
                NetworkState.LOADED -> {
                    initPlacesObserver()
                }
            }
        })
    }

    private fun initPlacesObserver() {
        placesViewModel.placesMutableLiveData.observe(this, { places ->
            if (places != null) {
                initPlacesRecyclerView(places.response?.groups?.get(0)?.items)
            }
        })

    }


    private fun initToggleRealTime() {
        binding.realTimeSC.isChecked = placesViewModel.isRealTime
        binding.realTimeSC.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                placesViewModel.setPeriodicRate(REALTIME)
            } else {
                placesViewModel.setPeriodicRate(ONETIME)
            }
        }


    }

    private fun initPlacesRecyclerView(products: List<FoursquareResponse.Response.Group.Item?>?) {
        binding.placesRV.adapter = adapter
        adapter?.submitList(products)
        binding.placesRV.scheduleLayoutAnimation()

    }

}