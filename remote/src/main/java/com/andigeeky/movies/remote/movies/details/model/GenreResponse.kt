package com.andigeeky.movies.remote.movies.details.model

import com.andigeeky.movies.data.movies.details.model.GenreEntity
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun GenreResponse.map(): GenreEntity {
    return GenreEntity(
        id = this.id,
        name = this.name
    )
}
