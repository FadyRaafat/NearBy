package com.fadynearby.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fadynearby.api.models.FoursquareResponse
import com.fadynearby.api.models.VenueImagesResponse
import com.fadynearby.repository.NetworkRepository
import com.fadynearby.repository.SharedPreferenceRepository
import com.fadynearby.ui.utils.LoadingState
import com.fadynearby.ui.utils.NetworkState
import com.fadynearby.ui.utils.SingleLiveEvent
import com.fadynearby.ui.utils.Utils
import com.fadynearby.ui.utils.Utils.MIN_DISTANCE
import com.fadynearby.ui.utils.Utils.MIN_TIME
import com.fadynearby.ui.utils.Utils.NUMBER_OF_RETRIES
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("MissingPermission", "CheckResult")
class PlacesViewModel(application: Application) :
    AndroidViewModel(application), LocationListener {
    var networkStatusMutableLiveData = SingleLiveEvent<NetworkState>()
    var networkRepository = NetworkRepository()
    var preferenceRepository = SharedPreferenceRepository(application)
    var userCurrentLocation = MutableLiveData<Location?>()
    var isRealTime = preferenceRepository.getPeriodicRate() == Utils.REALTIME
    var firstLaunch = true

    init {
        subscribeToUserLocation()
    }

    fun setupLocationManager(locationManager: LocationManager?) {
        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            MIN_TIME,
            MIN_DISTANCE,
            this
        )
        locationManager?.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            MIN_TIME,
            MIN_DISTANCE,
            this
        )

    }

    override fun onLocationChanged(location: Location) {
        when {
            firstLaunch -> {
                userCurrentLocation.value = location
                firstLaunch = false
            }
            else -> {
                if (isRealTime) {
                    userCurrentLocation.value = location
                }
            }
        }
    }

    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    private fun subscribeToUserLocation() {
        userCurrentLocation.observeForever { location ->
            if (location != null) {
                getPlaces(location.latitude.toString(), location.longitude.toString())
            }
        }
    }

    // Places
    var placesLoadingState: LoadingState = LoadingState()
    val placesMutableLiveData = MutableLiveData<FoursquareResponse>()

    private fun getPlaces(
        latitude: String, longitude: String,
    ) {
        networkStatusMutableLiveData.postValue(NetworkState.LOADING)
        placesLoadingState.loadingState()
        networkRepository.getPlaces(latitude, longitude)
            .subscribeOn(Schedulers.io())
            .retry(NUMBER_OF_RETRIES)
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { places ->
                    placesLoadingState.loadedState()
                    when {
                        places.response?.groups?.get(0)?.items.isNullOrEmpty() -> {
                            networkStatusMutableLiveData.postValue(NetworkState.EMPTY_RESPONSE)
                        }
                        else -> {
                            networkStatusMutableLiveData.postValue(NetworkState.LOADED)
                            placesMutableLiveData.postValue(places)
                        }
                    }
                },
                {
                    placesLoadingState.loadedState()
                    networkStatusMutableLiveData.postValue(NetworkState.FAILED)
                }
            )
    }

    fun getVenuePhotos(
        venueId: String?,
        onFinish: (Boolean, VenueImagesResponse?) -> Unit
    ) {
        networkRepository.getVenuePhotos(venueId)
            .subscribeOn(Schedulers.io())
            .retry(NUMBER_OF_RETRIES)
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { venueImages ->
                    onFinish.invoke(true, venueImages)

                },
                {
                }
            )
    }

    fun setPeriodicRate(rate: String) {
        when (rate) {
            Utils.REALTIME -> {
                isRealTime = true
                preferenceRepository.savePeriodicRate(rate)
            }
            Utils.ONETIME -> {
                isRealTime = false
                preferenceRepository.savePeriodicRate(rate)
            }
        }
    }


}