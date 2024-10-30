package com.app.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a Movie.
 *
 * @property id The unique identifier of the movie.
 * @property name The name of the movie.
 * @property runtimeInMinutes The runtime of the movie in minutes.
 * @property budgetInMillions The budget of the movie in millions.
 * @property boxOfficeRevenueInMillions The box office revenue of the movie in millions.
 * @property academyAwardNominations The number of Academy Award nominations the movie received.
 * @property academyAwardWins The number of Academy Awards the movie won.
 * @property rottenTomatoesScore The Rotten Tomatoes score of the movie.
 * @property quoteList The list of quotes from the movie.
 * @property characterList The list of characters in the movie.
 */
@Parcelize
data class Movie(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("runtimeInMinutes")
    var runtimeInMinutes: Int? = null,
    @SerializedName("budgetInMillions")
    var budgetInMillions: Double? = null,
    @SerializedName("boxOfficeRevenueInMillions")
    var boxOfficeRevenueInMillions: Double?  = null,
    @SerializedName("academyAwardNominations")
    var academyAwardNominations: Int? = null,
    @SerializedName("academyAwardWins")
    var academyAwardWins: Int? = null,
    @SerializedName("rottenTomatoesScore")
    var rottenTomatoesScore: Double? = null,
    var quoteList: List<Quote> = arrayListOf(),
    var characterList: ArrayList<Character> = arrayListOf()
): Parcelable