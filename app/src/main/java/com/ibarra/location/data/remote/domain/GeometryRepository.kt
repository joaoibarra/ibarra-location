package com.ibarra.location.data.remote.domain

import com.google.gson.annotations.SerializedName

class GeometryRepository (
    @SerializedName("location") val location: NearbyLocationRepository
)