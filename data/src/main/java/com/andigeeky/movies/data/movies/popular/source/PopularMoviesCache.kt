package com.andigeeky.movies.data.movies.popular.source

import com.andigeeky.movies.data.movies.popular.model.PopularMoviesEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface PopularMoviesCache {
    /**
     * Returns list of popular movies
     * @param  params page number
     */
    fun getMovies(params: Int?): Flowable<PopularMoviesEntity?>

    /**
     * Clears all saved popular movies
     */
    fun clearMovies() : Completable

    /**
     * Saves given list of movies
     */
    fun saveMovies(movies: PopularMoviesEntity?) : Completable
}
