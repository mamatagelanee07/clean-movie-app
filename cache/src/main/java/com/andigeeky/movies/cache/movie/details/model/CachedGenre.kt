package com.andigeeky.movies.cache.movie.details.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andigeeky.movies.data.movies.details.model.GenreEntity

@Entity
data class CachedGenre(
    @PrimaryKey
    val id: Int,
    val name: String?
)

@Entity(
    primaryKeys = ["movieId", "genreId"]
)
data class MovieGenreJoin(
    @ColumnInfo(index = true)
    val movieId: Int,
    @ColumnInfo(index = true)
    val genreId: Int
)

internal fun CachedGenre.map(): GenreEntity {
    return GenreEntity(
        id = this.id,
        name = this.name
    )
}

internal fun GenreEntity.map(): CachedGenre {
    return CachedGenre(
        id = this.id,
        name = this.name
    )
}
