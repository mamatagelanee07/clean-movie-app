package com.andigeeky.movies.remote.movies.details.model

import com.andigeeky.movies.data.movies.details.model.MovieDetailsEntity
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("genre")
    val genres: List<GenreResponse>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: Any?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryResponse>?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)

fun MovieDetailsResponse.map(): MovieDetailsEntity {
    return MovieDetailsEntity(
        adult = this.adult,
        belongsToCollection = this.belongsToCollection,
        budget = this.budget,
        id = this.id,
        popularity = this.popularity,
        posterPath = this.posterPath,
        revenue = this.revenue,
        runtime = this.runtime,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        backdropPath = this.backdropPath,
        genres = this.genres?.map { it.map() },
        homepage = this.homepage,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        productionCompanies = this.productionCompanies?.map { it.map() },
        productionCountries = this.productionCountries?.map { it.map() },
        releaseDate = this.releaseDate,
        spokenLanguages = this.spokenLanguages?.map { it.map() },
        status = this.status,
        tagline = this.tagline,
        title = this.title
    )
}
