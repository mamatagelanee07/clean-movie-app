package com.andigeeky.movies.cache.movie

import com.andigeeky.movies.cache.movie.popular.model.CachedMovie
import com.andigeeky.movies.data.movies.popular.model.MovieEntity

internal object MoviesFactory {
    fun getMoviesCache(count: Int) : List<CachedMovie>{
        val movies = mutableListOf<CachedMovie>()
        repeat(count) { index ->
            movies.add(
                CachedMovie(
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

    fun getMoviesEntity(count: Int) : List<MovieEntity>{
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
