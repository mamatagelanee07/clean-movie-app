package com.andigeeky.movies.cache.movie.popular.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.andigeeky.movies.cache.movie.db.MovieDBQueries
import com.andigeeky.movies.cache.movie.db.convertors.IntListTypeConverter
import com.andigeeky.movies.data.movies.popular.model.MovieEntity

@Entity(tableName = MovieDBQueries.TABLE_POPULAR_MOVIES)
@TypeConverters(IntListTypeConverter::class)
data class CachedMovie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    @PrimaryKey
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

fun MovieEntity.map() : CachedMovie{
    return CachedMovie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun CachedMovie.map() : MovieEntity{
    return MovieEntity(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}
