package com.ibarra.location.data.remote.domain

import com.google.gson.annotations.SerializedName

class NearbyPlaceRepository (
    @SerializedName("geometry") val geometry: GeometryRepository?,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("opening_hours") val openingHours: OpeningHoursRepository?,
    @SerializedName("place_id") val place_id: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("user_ratings_total") val userRatingsTotal: Int,
    @SerializedName("vicinity") val vicinity: String,
    @SerializedName("photos") val photos: List<PhotoRepository>?
)