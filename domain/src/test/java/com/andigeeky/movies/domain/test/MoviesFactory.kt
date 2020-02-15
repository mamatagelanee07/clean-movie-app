package com.andigeeky.movies.domain.test

import com.andigeeky.movies.domain.movies.detail.model.*
import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.domain.movies.popular.model.PopularMovies

internal object MoviesFactory {
    fun getPopularMovies(page: Int, size: Int): PopularMovies {
        return PopularMovies(
            page = page,
            totalPages = page * 5,
            totalResults = page * 10,
            results = getMovies(size)
        )
    }

    private fun getMovies(count: Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) { index ->
            movies.add(
                Movie(
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

    fun getMovieDetail(id: Int, size: Int): MovieDetails {
        return MovieDetails(
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
            genres = getGenre(size),
            productionCompanies = getProductionCompany(size),
            productionCountries = getProductionCountry(size),
            spokenLanguages = getSpokenLanguage(size)
        )
    }

    private fun getGenre(size: Int) : List<Genre>{
        val genres = mutableListOf<Genre>()
        repeat(size) {
            genres.add(
                Genre(
                    id = it,
                    name = "name $it"
                )
            )
        }
        return genres
    }

    private fun getProductionCompany(size: Int) : List<ProductionCompany>{
        val list = mutableListOf<ProductionCompany>()
        repeat(size) {
            list.add(
                ProductionCompany(
                    id = it,
                    name = "name $it",
                    originCountry = "originCountry $it",
                    logoPath = "logoPath $it"
                )
            )
        }
        return list
    }

    private fun getProductionCountry(size: Int) : List<ProductionCountry>{
        val list = mutableListOf<ProductionCountry>()
        repeat(size) {
            list.add(
                ProductionCountry(
                    iso31661 = it.toString(),
                    name = "name $it"
                )
            )
        }
        return list
    }

    private fun getSpokenLanguage(size: Int) : List<SpokenLanguage>{
        val list = mutableListOf<SpokenLanguage>()
        repeat(size) {
            list.add(
                SpokenLanguage(
                    iso6391 = it.toString(),
                    name = "name $it"
                )
            )
        }
        return list
    }
}
