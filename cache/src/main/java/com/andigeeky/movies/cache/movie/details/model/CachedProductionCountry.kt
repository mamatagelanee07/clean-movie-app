package com.andigeeky.movies.cache.movie.details.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andigeeky.movies.data.movies.details.model.ProductionCountryEntity

@Entity
data class CachedProductionCountry(
    @PrimaryKey
    val iso31661: String,
    val name: String?
)


@Entity(
    primaryKeys = ["movieId", "productionCountryId"]
)
data class MovieProductionCountryJoin(
    @ColumnInfo(index = true)
    val movieId: Int,
    @ColumnInfo(index = true)
    val productionCountryId: String
)

internal fun CachedProductionCountry.map() : ProductionCountryEntity{
    return ProductionCountryEntity(
        iso31661 = this.iso31661,
        name = this.name
    )
}

internal fun ProductionCountryEntity.map() : CachedProductionCountry{
    return CachedProductionCountry(
        iso31661 = this.iso31661,
        name = this.name
    )
}
