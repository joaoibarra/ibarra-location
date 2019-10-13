package com.ibarra.location.data.remote.domain

import com.google.gson.annotations.SerializedName

class NearbyPlaceResponse (
    @SerializedName("next_page_token") val nextPageToken: String,
    @SerializedName("results") val results: List<NearbyPlaceRepository>
)