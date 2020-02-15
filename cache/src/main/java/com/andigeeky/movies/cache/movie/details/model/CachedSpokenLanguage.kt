package com.andigeeky.movies.cache.movie.details.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andigeeky.movies.data.movies.details.model.SpokenLanguageEntity


@Entity
data class CachedSpokenLanguage(
    @PrimaryKey
    val iso6391: String,
    val name: String?
)

@Entity(
    primaryKeys = ["movieId", "spokenLanguageId"]
)
data class MovieSpokenLanguageJoin(
    @ColumnInfo(index = true)
    val movieId: Int,
    @ColumnInfo(index = true)
    val spokenLanguageId: String
)

internal fun CachedSpokenLanguage.map() : SpokenLanguageEntity{
    return SpokenLanguageEntity(
        iso6391 = this.iso6391,
        name = this.name
    )
}

internal fun SpokenLanguageEntity.map() : CachedSpokenLanguage{
    return CachedSpokenLanguage(
        iso6391 = this.iso6391,
        name = this.name
    )
}
