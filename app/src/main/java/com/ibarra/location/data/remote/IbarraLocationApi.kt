package com.ibarra.location.data.remote

import com.ibarra.location.BuildConfig
import com.ibarra.location.data.remote.domain.NearbyPlace
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IbarraLocationApi {
    @GET("maps/api/place/nearbysearch/output")
    fun getNearbyPlaces(@Query("type") type: String = "cafe",
                        @Query("location") location: String = "52.3545362,4.7638781",
                        @Query("radius") radius: String = "10000",
                        @Query("key") key: String = BuildConfig.API_KEY): Observable<NearbyPlace>
}