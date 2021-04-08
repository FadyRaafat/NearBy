package com.fadynearby.ui.utils

import com.fadynearby.api.models.FoursquareResponse
import com.fadynearby.api.models.VenueImagesResponse
import java.util.*


object Utils {

    const val FOURSQUARE_CLIENT_ID = "GLSATBSPZ3KERTNWWZNJ0P4J5B1DP2HEEDMXSSNHTKFVYFT5"
    const val FOURSQUARE_SECRET_KEY = "GYIYG1I5HHMW52LJIWBUV53ZT3PWJNXKHQKLQC4IPABIE34O"
    const val VERSION_DATE = "20210704"
    const val BASE_URL = "https://api.foursquare.com/v2/venues/"
    const val MIN_DISTANCE = 500F
    const val MIN_TIME = 2000L
    const val NUMBER_OF_RETRIES = 3L
    const val REALTIME = "realtime"
    const val ONETIME = "onetime"

    fun extractImageUrl(venueImagesResponse: VenueImagesResponse?): String {
        val images = venueImagesResponse?.response?.photos?.items?.get(0)
        return "${images?.prefix}${images?.width}x${images?.height}${images?.suffix}"
    }

    fun extractVenueAddress(venue: FoursquareResponse.Response.Group.Item): String? {
        return venue.venue?.location?.formattedAddress?.joinToString(", ")
    }


}