package com.fadynearby.viewmodels

import android.app.Application
import android.location.Location
import com.fadynearby.ui.utils.Utils
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PlacesViewModelTest {
    val placesViewModel = PlacesViewModel(Application())

    @Test
    fun setPeriodicRate() {
        placesViewModel.setPeriodicRate(Utils.REALTIME)
        val result = placesViewModel.preferenceRepository.getPeriodicRate()
        assertThat(result).isEqualTo(Utils.REALTIME)
    }


    @Test
    fun onLocationChanged() {
        val location = Location("dummyprovider")
        location.latitude = VenuesMockResponse.location.lat!!
        location.longitude = VenuesMockResponse.location.lng!!
        placesViewModel.onLocationChanged(location)
        assertThat(placesViewModel.userCurrentLocation.value?.latitude).isEqualTo(10.1)
    }

    @Test
    fun getPlacesResponse() {
        val result = placesViewModel.networkRepository.getPlaces(10.1.toString(), 10.1.toString())
        assertThat(result).isNotNull()
    }
}
