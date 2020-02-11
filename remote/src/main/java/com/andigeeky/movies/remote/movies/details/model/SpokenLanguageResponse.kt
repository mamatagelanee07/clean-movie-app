package com.andigeeky.movies.remote.movies.details.model

import com.andigeeky.movies.data.movies.details.model.SpokenLanguageEntity
import com.google.gson.annotations.SerializedName

data class SpokenLanguageResponse(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
)

fun SpokenLanguageResponse.map() : SpokenLanguageEntity {
    return SpokenLanguageEntity(
        iso6391 = this.iso6391,
        name = this.name
    )
}
