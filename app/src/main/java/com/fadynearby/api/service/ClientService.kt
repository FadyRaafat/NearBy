package com.fadynearby.api.service

import com.fadynearby.api.LiveDataCallAdapterFactory
import com.fadynearby.api.models.FoursquareResponse
import com.fadynearby.api.models.VenueImagesResponse
import com.fadynearby.ui.utils.Utils.BASE_URL
import com.fadynearby.ui.utils.Utils.FOURSQUARE_CLIENT_ID
import com.fadynearby.ui.utils.Utils.FOURSQUARE_SECRET_KEY
import com.fadynearby.ui.utils.Utils.VERSION_DATE
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientService {

    companion object {
        fun getClient(): ClientService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(HttpClient.getClient())
                .build()

            return retrofit.create(ClientService::class.java)
        }

    }

    @GET("explore")
    fun places(
        @Query("near") lngLat: String,
        @Query("client_id") clientId: String = FOURSQUARE_CLIENT_ID,
        @Query("client_secret") clientSecret: String = FOURSQUARE_SECRET_KEY,
        @Query("v") versioning: String = VERSION_DATE
    ): Observable<FoursquareResponse>

    @GET("{venueId}/photos")
    fun venuePhotos(
        @Path("venueId") venueId: String?,
        @Query("client_id") clientId: String = FOURSQUARE_CLIENT_ID,
        @Query("client_secret") clientSecret: String = FOURSQUARE_SECRET_KEY,
        @Query("v") versioning: String = VERSION_DATE
        ): Observable<VenueImagesResponse>

}