package com.ibarra.location.data.remote.domain

import com.google.gson.annotations.SerializedName

class OpeningHoursRepository (
    @SerializedName("open_now") val openNow: Boolean
)