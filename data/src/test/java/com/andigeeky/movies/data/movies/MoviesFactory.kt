package com.andigeeky.movies.data.movies

import com.andigeeky.movies.data.movies.details.model.*
import com.andigeeky.movies.data.movies.popular.model.MovieEntity
import com.andigeeky.movies.data.movies.popular.model.PopularMoviesEntity

internal object MoviesFactory {

    fun getMovieDetailEntity(id: Int, size: Int): MovieDetailsEntity {
        return MovieDetailsEntity(
            adult = false,
            belongsToCollection = false,
            budget = id * 10000,
            id = id,
            popularity = id * 0.1,
            posterPath = null,
            revenue = id,
            runtime = id,
            video = id % 2 == 0,
            voteAverage = id * 0.3,
            voteCount = id,
            backdropPath = "",
            status = "",
            homepage = "",
            imdbId = "",
            originalLanguage = "",
            overview = "",
            originalTitle = "",
            releaseDate = "",
            tagline = "",
            title = "",
            genres = getGenreEntity(size),
            productionCompanies = getProductionCompanyEntity(size),
            productionCountries = getProductionCountryEntity(size),
            spokenLanguages = getSpokenLanguageEntity(size)
        )
    }

    private fun getGenreEntity(size: Int) : List<GenreEntity>{
        val genres = mutableListOf<GenreEntity>()
        repeat(size) {
            genres.add(
                GenreEntity(
                    id = it,
                    name = "name $it"
                )
            )
        }
        return genres
    }

    private fun getProductionCompanyEntity(size: Int) : List<ProductionCompanyEntity>{
        val list = mutableListOf<ProductionCompanyEntity>()
        repeat(size) {
            list.add(
                ProductionCompanyEntity(
                    id = it,
                    name = "name $it",
                    originCountry = "originCountry $it",
                    logoPath = "logoPath $it"
                )
            )
        }
        return list
    }

    private fun getProductionCountryEntity(size: Int) : List<ProductionCountryEntity>{
        val list = mutableListOf<ProductionCountryEntity>()
        repeat(size) {
            list.add(
                ProductionCountryEntity(
                    iso31661 = it.toString(),
                    name = "name $it"
                )
            )
        }
        return list
    }

    private fun getSpokenLanguageEntity(size: Int) : List<SpokenLanguageEntity>{
        val list = mutableListOf<SpokenLanguageEntity>()
        repeat(size) {
            list.add(
                SpokenLanguageEntity(
                    iso6391 = it.toString(),
                    name = "name $it"
                )
            )
        }
        return list
    }
    fun getPopularMovies(page: Int, size: Int) : PopularMoviesEntity {
        return PopularMoviesEntity(
            page = page,
            totalPages = page * 5,
            totalResults = page * 10,
            results = getMoviesEntity(size)
        )
    }
    private fun getMoviesEntity(count: Int) : List<MovieEntity>{
        val movies = mutableListOf<MovieEntity>()
        repeat(count) { index ->
            movies.add(
                MovieEntity(
                    adult = false,
                    backdropPath = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
                    genreIds = listOf(index, index * 10, index * 20),
                    id = index,
                    originalLanguage = "en",
                    originalTitle = "Suicide Squad",
                    overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                    popularity = 30.690177,
                    posterPath = "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
                    releaseDate = "2016-08-03",
                    title = "Suicide Squad",
                    video = false,
                    voteAverage = 5.91,
                    voteCount = 1466
                )
            )
        }
        return movies
    }
}
