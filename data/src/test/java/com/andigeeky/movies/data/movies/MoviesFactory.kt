package com.andigeeky.movies.data.movies

import com.andigeeky.movies.data.movies.details.model.MovieDetailsEntity
import com.andigeeky.movies.data.movies.popular.model.MovieEntity
import com.andigeeky.movies.data.movies.popular.model.PopularMoviesEntity

internal object MoviesFactory {

    fun getMovieDetails(id: Int): MovieDetailsEntity {
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
            voteCount = id
        )
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
