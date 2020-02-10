package com.andigeeky.movies.domain.movies.detail.model

data class MovieDetails(
    val adult: Boolean?,
    val backdropPath: String = "",
    val belongsToCollection: Any? = "",
    val budget: Int?,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = "",
    val id: Int,
    val imdbId: String? = "",
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double?,
    val posterPath: Any? ,
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val releaseDate: String? = "",
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String? ="",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)

data class ProductionCountry(
    val iso31661: String,
    val name: String
)

data class SpokenLanguage(
    val iso6391: String,
    val name: String
)
