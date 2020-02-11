package com.andigeeky.movies.data.movies.details

import com.andigeeky.movies.data.movies.details.model.*
import com.andigeeky.movies.domain.movies.detail.model.*

fun MovieDetailsEntity.map(): MovieDetails {
    return MovieDetails(
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
        productionCompanies = this.productionCompanies.map { it.map() },
        productionCountries = this.productionCountries.map { it.map() },
        releaseDate = this.releaseDate,
        spokenLanguages = this.spokenLanguages.map { it.map() },
        status = this.status,
        tagline = this.tagline,
        title = this.title
    )
}

fun MovieDetails.map(): MovieDetailsEntity {
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
        productionCompanies = this.productionCompanies.map { it.map() },
        productionCountries = this.productionCountries.map { it.map() },
        releaseDate = this.releaseDate,
        spokenLanguages = this.spokenLanguages.map { it.map() },
        status = this.status,
        tagline = this.tagline,
        title = this.title
    )
}

fun GenreEntity.map(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun Genre.map(): GenreEntity {
    return GenreEntity(
        id = this.id,
        name = this.name
    )
}

fun ProductionCompanyEntity.map(): ProductionCompany {
    return ProductionCompany(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
    )
}

fun ProductionCompany.map(): ProductionCompanyEntity {
    return ProductionCompanyEntity(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
    )
}

fun ProductionCountryEntity.map() : ProductionCountry {
    return ProductionCountry(
        iso31661 = this.iso31661,
        name = this.name
    )
}

fun ProductionCountry.map() : ProductionCountryEntity {
    return ProductionCountryEntity(
        iso31661 = this.iso31661,
        name = this.name
    )
}

fun SpokenLanguageEntity.map() : SpokenLanguage {
    return SpokenLanguage(
        iso6391 = this.iso6391,
        name = this.name
    )
}

fun SpokenLanguage.map() : SpokenLanguageEntity {
    return SpokenLanguageEntity(
        iso6391 = this.iso6391,
        name = this.name
    )
}
