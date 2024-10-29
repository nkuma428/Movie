package com.app.movie.data.model

import com.google.gson.annotations.SerializedName

data class Quote (
    @SerializedName("_id")
    var id : String? = null,
    @SerializedName("dialog")
    var dialog : String? = null,
    @SerializedName("movie")
    var movie : String? = null,
    @SerializedName("character")
    var character : String? = null
)