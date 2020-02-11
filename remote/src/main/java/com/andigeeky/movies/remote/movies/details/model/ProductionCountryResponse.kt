package com.andigeeky.movies.remote.movies.details.model

import com.andigeeky.movies.data.movies.details.model.ProductionCountryEntity
import com.google.gson.annotations.SerializedName

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)

fun ProductionCountryResponse.map() : ProductionCountryEntity {
    return ProductionCountryEntity(
        iso31661 = this.iso31661,
        name = this.name
    )
}
