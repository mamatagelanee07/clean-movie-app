package com.andigeeky.movies.cache.movie.details.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andigeeky.movies.data.movies.details.model.ProductionCompanyEntity

@Entity
data class CachedProductionCompany(
    @PrimaryKey
    val id: Int,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
)

@Entity(
    primaryKeys = ["movieId", "productionCompanyId"]
)
data class MovieProductionCompanyJoin(
    @ColumnInfo(index = true)
    val movieId: Int,
    @ColumnInfo(index = true)
    val productionCompanyId: Int
)

internal fun CachedProductionCompany.map() : ProductionCompanyEntity{
    return ProductionCompanyEntity(
        id = this.id,
        name = this.name,
        logoPath = this.logoPath,
        originCountry = this.originCountry
    )
}

internal fun ProductionCompanyEntity.map() : CachedProductionCompany{
    return CachedProductionCompany(
        id = this.id,
        name = this.name,
        logoPath = this.logoPath,
        originCountry = this.originCountry
    )
}
