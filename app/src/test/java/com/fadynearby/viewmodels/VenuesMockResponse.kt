package com.fadynearby.viewmodels

import com.fadynearby.api.models.FoursquareResponse

class VenuesMockResponse {

    companion object {
        var list = listOf("one", "two", "three")
        var location: FoursquareResponse.Response.Group.Item.Venue.Location =
            FoursquareResponse.Response.Group.Item.Venue.Location(
                "test address",
                "test cc",
                "test city",
                "test country",
                "test cross street",
                list,
                null,
                10.1,
                10.1,
                "",
                "",
                ""
            )
    }


}
