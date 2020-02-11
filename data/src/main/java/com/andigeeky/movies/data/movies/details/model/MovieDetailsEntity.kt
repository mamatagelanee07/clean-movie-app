package com.andigeeky.movies.data.movies.details.model

data class MovieDetailsEntity(
    val adult: Boolean?,
    val backdropPath: String = "",
    val belongsToCollection: Any? = "",
    val budget: Int?,
    val genres: List<GenreEntity>? = emptyList(),
    val homepage: String? = "",
    val id: Int,
    val imdbId: String? = "",
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double?,
    val posterPath: Any? ,
    val productionCompanies: List<ProductionCompanyEntity> = emptyList(),
    val productionCountries: List<ProductionCountryEntity> = emptyList(),
    val releaseDate: String? = "",
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguageEntity> = emptyList(),
    val status: String? ="",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

data class GenreEntity(
    val id: Int,
    val name: String
)

data class ProductionCompanyEntity(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)

data class ProductionCountryEntity(
    val iso31661: String,
    val name: String
)

data class SpokenLanguageEntity(
    val iso6391: String,
    val name: String
)
