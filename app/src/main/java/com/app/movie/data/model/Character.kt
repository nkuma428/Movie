package com.app.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a Character in the movie.
 *
 * @property id The unique identifier of the character.
 * @property name The name of the character.
 * @property wikiUrl The URL to the character's wiki page.
 * @property race The race of the character.
 * @property birth The birth date of the character.
 * @property gender The gender of the character.
 * @property death The death date of the character.
 * @property hair The hair color of the character.
 * @property height The height of the character.
 * @property realm The realm of the character.
 * @property spouse Indicates if the character has a spouse.
 */
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