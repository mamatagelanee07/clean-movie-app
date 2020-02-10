package com.andigeeky.movies.remote.movies.popular.model

import com.andigeeky.movies.data.movies.popular.model.MovieEntity
import com.andigeeky.movies.data.movies.popular.model.PopularMoviesEntity
import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<RemoteMovie?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

data class RemoteMovie(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)

const val PATH_IMAGE = "https://image.tmdb.org/t/p/w500/"

fun PopularMovieResponse.mapEntity() : PopularMoviesEntity {
    return PopularMoviesEntity(
        page = this.page,
        results = this.results?.map { it?.mapEntity() },
        totalResults = this.totalResults,
        totalPages = this.totalPages
    )
}

fun PopularMoviesEntity .map() : PopularMovieResponse{
    return PopularMovieResponse(
        page = this.page,
        results = this.results?.map { it?.map() },
        totalResults = this.totalResults,
        totalPages = this.totalPages
    )
}

internal fun RemoteMovie.mapEntity() : MovieEntity {
    return MovieEntity(
        adult = this.adult,
        backdropPath = PATH_IMAGE+this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = PATH_IMAGE+this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

internal fun MovieEntity.map() : RemoteMovie{
    return RemoteMovie(
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

