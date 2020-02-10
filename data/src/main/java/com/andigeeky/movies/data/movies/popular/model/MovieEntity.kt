package com.andigeeky.movies.data.movies.popular.model

import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.domain.movies.popular.model.PopularMovies

data class PopularMoviesEntity(
    val page: Int,
    val results: List<MovieEntity?>?,
    val totalPages: Int?,
    val totalResults: Int?
)

data class MovieEntity(
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

fun PopularMovies.mapEntity() : PopularMoviesEntity{
    return PopularMoviesEntity(
        page = this.page,
        results = this.results?.map { it?.mapEntity() },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun PopularMoviesEntity.mapEntity() : PopularMovies{
    return PopularMovies(
        page = this.page,
        results = this.results?.map { it?.map() },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}


internal fun Movie.mapEntity() : MovieEntity{
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

internal fun MovieEntity.map() : Movie{
    return Movie(
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
