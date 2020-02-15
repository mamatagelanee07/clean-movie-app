package com.andigeeky.movies.data.movies.popular.source

import com.andigeeky.movies.data.movies.popular.model.PopularMoviesEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PopularMoviesCache {
    /**
     * Returns list of popular movies
     * @param  params page number
     */
    fun getMovies(params: Int?): Single<PopularMoviesEntity?>

    /**
     * Clears all saved popular movies
     */
    fun clearMovies() : Completable

    /**
     * Saves given list of movies
     */
    fun saveMovies(movies: PopularMoviesEntity?) : Completable
}
