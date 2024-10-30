package com.app.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a Quote in the movie.
 *
 * @property id The unique identifier of the quote.
 * @property dialog The dialog spoken by the character in the movie.
 * @property movie The identifier of the movie in which the quote appears.
 * @property character The identifier of the character who speaks the quote.
 */
@Parcelize
data class Quote (
    @SerializedName("_id")
    var id : String? = null,
    @SerializedName("dialog")
    var dialog : String? = null,
    @SerializedName("movie")
    var movie : String? = null,
    @SerializedName("character")
    var character : String? = null
) : Parcelable