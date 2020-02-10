package com.andigeeky.movies.domain.test

import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.domain.movies.popular.model.PopularMovies

internal object MoviesFactory {
    fun getPopularMovies(page: Int, size: Int) : PopularMovies{
        return PopularMovies(
            page = page,
            totalPages = page * 5,
            totalResults = page * 10,
            results = getMovies(size)
        )
    }

    private fun getMovies(count: Int) : List<Movie>{
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
}
