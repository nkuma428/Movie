package com.app.movie.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a list of characters.
 *
 * @property characterList The list of characters.
 * @property total The total number of characters.
 * @property limit The limit of characters per page.
 * @property offset The offset for pagination.
 * @property page The current page number.
 * @property pages The total number of pages.
 */
data class CharacterResponse(
    @SerializedName("docs")
    var characterList : ArrayList<Character> = arrayListOf(),
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