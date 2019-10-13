package com.ibarra.location.data.remote

import com.ibarra.location.BuildConfig
import com.ibarra.location.data.remote.domain.NearbyPlaceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IbarraLocationApi {
    @GET("maps/api/place/nearbysearch/json")
    fun getNearbyPlaces(@Query("type") type: String? = "cafe",
                        @Query("pagetoken") pageToken: String? = "",
                        @Query("location") location: String = "52.3545362,4.7638781",
                        @Query("rankby") rankBy: String = "distance",
                        @Query("key") key: String = BuildConfig.API_KEY): Single<NearbyPlaceResponse>
}