package com.app.movie.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a list of quotes.
 *
 * @property quoteList The list of quotes.
 * @property total The total number of quotes.
 * @property limit The limit of quotes per page.
 * @property offset The offset for pagination.
 * @property page The current page number.
 * @property pages The total number of pages.
 */
data class QuoteResponse(
    @SerializedName("docs")
    var quoteList : ArrayList<Quote> = arrayListOf(),
    @SerializedName("total")
    var total : Int? = null,
    @SerializedName("limit")
    var limit : Int? = null,
    @SerializedName("offset")
    var offset : Int? = null,
    @SerializedName("page")
    var page : Int? = null,
    @SerializedName("pages")
    var pages : Int? = null
)