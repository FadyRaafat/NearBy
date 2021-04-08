package com.fadynearby.api.models


import com.google.gson.annotations.SerializedName


data class FoursquareResponse(
    @SerializedName("meta")
    var meta: Meta?,
    @SerializedName("response")
    var response: Response?
) {

    data class Meta(
        @SerializedName("code")
        var code: Int?,
        @SerializedName("requestId")
        var requestId: String?
    )


    data class Response(
        @SerializedName("geocode")
        var geocode: Geocode?,
        @SerializedName("groups")
        var groups: List<Group?>?,
        @SerializedName("headerFullLocation")
        var headerFullLocation: String?,
        @SerializedName("headerLocation")
        var headerLocation: String?,
        @SerializedName("headerLocationGranularity")
        var headerLocationGranularity: String?,
        @SerializedName("suggestedBounds")
        var suggestedBounds: SuggestedBounds?,
        @SerializedName("suggestedFilters")
        var suggestedFilters: SuggestedFilters?,
        @SerializedName("totalResults")
        var totalResults: Int?
    ) {

        data class Geocode(
            @SerializedName("cc")
            var cc: String?,
            @SerializedName("center")
            var center: Center?,
            @SerializedName("displayString")
            var displayString: String?,
            @SerializedName("what")
            var what: String?,
            @SerializedName("where")
            var `where`: String?
        ) {

            data class Center(
                @SerializedName("lat")
                var lat: Double?,
                @SerializedName("lng")
                var lng: Double?
            )
        }


        data class Group(
            @SerializedName("items")
            var items: List<Item?>?,
            @SerializedName("name")
            var name: String?,
            @SerializedName("type")
            var type: String?
        ) {

            data class Item(
                @SerializedName("reasons")
                var reasons: Reasons?,
                @SerializedName("referralId")
                var referralId: String?,
                @SerializedName("venue")
                var venue: Venue?
            ) {

                data class Reasons(
                    @SerializedName("count")
                    var count: Int?,
                    @SerializedName("items")
                    var items: List<ReasonItem?>?
                ) {

                    data class ReasonItem(
                        @SerializedName("reasonName")
                        var reasonName: String?,
                        @SerializedName("summary")
                        var summary: String?,
                        @SerializedName("type")
                        var type: String?
                    )
                }


                data class Venue(
                    @SerializedName("categories")
                    var categories: List<Category?>?,
                    @SerializedName("delivery")
                    var delivery: Delivery?,
                    @SerializedName("id")
                    var id: String?,
                    @SerializedName("location")
                    var location: Location?,
                    @SerializedName("name")
                    var name: String?,
                    @SerializedName("photos")
                    var photos: Photos?,
                    @SerializedName("venuePage")
                    var venuePage: VenuePage?
                ) {

                    data class Category(
                        @SerializedName("icon")
                        var icon: Icon?,
                        @SerializedName("id")
                        var id: String?,
                        @SerializedName("name")
                        var name: String?,
                        @SerializedName("pluralName")
                        var pluralName: String?,
                        @SerializedName("primary")
                        var primary: Boolean?,
                        @SerializedName("shortName")
                        var shortName: String?
                    ) {

                        data class Icon(
                            @SerializedName("prefix")
                            var prefix: String?,
                            @SerializedName("suffix")
                            var suffix: String?
                        )
                    }


                    data class Delivery(
                        @SerializedName("id")
                        var id: String?,
                        @SerializedName("provider")
                        var provider: Provider?,
                        @SerializedName("url")
                        var url: String?
                    ) {

                        data class Provider(
                            @SerializedName("icon")
                            var icon: Icon?,
                            @SerializedName("name")
                            var name: String?
                        ) {

                            data class Icon(
                                @SerializedName("name")
                                var name: String?,
                                @SerializedName("prefix")
                                var prefix: String?,
                                @SerializedName("sizes")
                                var sizes: List<Int?>?
                            )
                        }
                    }


                    data class Location(
                        @SerializedName("address")
                        var address: String?,
                        @SerializedName("cc")
                        var cc: String?,
                        @SerializedName("city")
                        var city: String?,
                        @SerializedName("country")
                        var country: String?,
                        @SerializedName("crossStreet")
                        var crossStreet: String?,
                        @SerializedName("formattedAddress")
                        var formattedAddress: List<String?>?,
                        @SerializedName("labeledLatLngs")
                        var labeledLatLngs: List<LabeledLatLng?>?,
                        @SerializedName("lat")
                        var lat: Double?,
                        @SerializedName("lng")
                        var lng: Double?,
                        @SerializedName("neighborhood")
                        var neighborhood: String?,
                        @SerializedName("postalCode")
                        var postalCode: String?,
                        @SerializedName("state")
                        var state: String?
                    ) {

                        data class LabeledLatLng(
                            @SerializedName("label")
                            var label: String?,
                            @SerializedName("lat")
                            var lat: Double?,
                            @SerializedName("lng")
                            var lng: Double?
                        )
                    }


                    data class Photos(
                        @SerializedName("count")
                        var count: Int?,
                        @SerializedName("groups")
                        var groups: List<Any?>?
                    )


                    data class VenuePage(
                        @SerializedName("id")
                        var id: String?
                    )
                }
            }
        }


        data class SuggestedBounds(
            @SerializedName("ne")
            var ne: Ne?,
            @SerializedName("sw")
            var sw: Sw?
        ) {

            data class Ne(
                @SerializedName("lat")
                var lat: Double?,
                @SerializedName("lng")
                var lng: Double?
            )


            data class Sw(
                @SerializedName("lat")
                var lat: Double?,
                @SerializedName("lng")
                var lng: Double?
            )
        }


        data class SuggestedFilters(
            @SerializedName("filters")
            var filters: List<Filter?>?,
            @SerializedName("header")
            var header: String?
        ) {

            data class Filter(
                @SerializedName("key")
                var key: String?,
                @SerializedName("name")
                var name: String?
            )
        }
    }
}