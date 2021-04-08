package com.fadynearby.repository

import com.fadynearby.api.models.FoursquareResponse
import com.fadynearby.api.models.VenueImagesResponse
import com.fadynearby.api.service.ClientService
import io.reactivex.Observable

class NetworkRepository() {
    var clientService = ClientService.getClient()

    fun getPlaces(latitude: String, longitude: String): Observable<FoursquareResponse> {
        return clientService.places("$latitude,$longitude")
    }

    fun getVenuePhotos(venueId: String?): Observable<VenueImagesResponse> {
        return clientService.venuePhotos(venueId)
    }

}