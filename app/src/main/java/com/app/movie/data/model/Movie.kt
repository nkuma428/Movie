package com.app.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    var rottenTomatoesScore : Double? = null,
    var quoteList : ArrayList<Quote> = arrayListOf(),
    var characterList : ArrayList<Character> = arrayListOf()
): Parcelable