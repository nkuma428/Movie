package com.app.movie.data.model

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("_id")
    var id : String? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("runtimeInMinutes")
    var runtimeInMinutes : Int? = null,
    @SerializedName("budgetInMillions")
    var budgetInMillions : Double? = null,
    @SerializedName("boxOfficeRevenueInMillions")
    var boxOfficeRevenueInMillions : Double?  = null,
    @SerializedName("academyAwardNominations")
    var academyAwardNominations : Int? = null,
    @SerializedName("academyAwardWins")
    var academyAwardWins : Int? = null,
    @SerializedName("rottenTomatoesScore")
    var rottenTomatoesScore : Double? = null
)