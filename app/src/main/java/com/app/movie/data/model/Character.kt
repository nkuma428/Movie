package com.app.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character (
    @SerializedName("_id")
    var id : String? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("wikiUrl")
    var wikiUrl : String? = null,
    @SerializedName("race")
    var race : String? = null,
    @SerializedName("birth")
    var birth : String? = null,
    @SerializedName("gender")
    var gender : String? = null,
    @SerializedName("death")
    var death : String? = null,
    @SerializedName("hair")
    var hair : String? = null,
    @SerializedName("height")
    var height : String? = null,
    @SerializedName("realm")
    var realm : String? = null,
    @SerializedName("spouse")
    var spouse : Boolean? = null
): Parcelable