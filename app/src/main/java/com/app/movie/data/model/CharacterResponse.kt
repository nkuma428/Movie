package com.app.movie.data.model

import com.google.gson.annotations.SerializedName

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