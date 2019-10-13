package com.ibarra.location.data.remote.domain

import com.google.gson.annotations.SerializedName

class NearbyLocationRepository (
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double
)