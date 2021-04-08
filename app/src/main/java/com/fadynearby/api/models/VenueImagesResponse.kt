package com.fadynearby.api.models


import com.google.gson.annotations.SerializedName

data class VenueImagesResponse(
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
        @SerializedName("photos")
        var photos: Photos?
    ) {

        data class Photos(
            @SerializedName("count")
            var count: Int?,
            @SerializedName("dupesRemoved")
            var dupesRemoved: Int?,
            @SerializedName("items")
            var items: List<Item?>?
        ) {

            data class Item(
                @SerializedName("checkin")
                var checkin: Checkin?,
                @SerializedName("createdAt")
                var createdAt: Int?,
                @SerializedName("height")
                var height: Int?,
                @SerializedName("id")
                var id: String?,
                @SerializedName("prefix")
                var prefix: String?,
                @SerializedName("source")
                var source: Source?,
                @SerializedName("suffix")
                var suffix: String?,
                @SerializedName("visibility")
                var visibility: String?,
                @SerializedName("width")
                var width: Int?
            ) {

                data class Checkin(
                    @SerializedName("createdAt")
                    var createdAt: Int?,
                    @SerializedName("id")
                    var id: String?,
                    @SerializedName("timeZoneOffset")
                    var timeZoneOffset: Int?,
                    @SerializedName("type")
                    var type: String?
                )


                data class Source(
                    @SerializedName("name")
                    var name: String?,
                    @SerializedName("url")
                    var url: String?
                )
            }
        }
    }
}