package com.ibarra.location.data.remote.domain

import com.google.gson.annotations.SerializedName

class PhotoRepository (
    @SerializedName("height") val height: Double,
    @SerializedName("width") val width: Double,
    @SerializedName("photo_reference") val photoReference: String,
    @SerializedName("html_attributions") val htmlAttributions: List<String?>

)