package com.andigeeky.movies.domain.movies.popular.repository

import com.andigeeky.movies.domain.movies.popular.model.Movie
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Contains list of popular movies
 * @see <a href="https://developers.themoviedb.org/3/movies/get-popular-movies">Popular Movies Data</a>
 */
interface PopularMoviesRepository {

    /**
     * Returns list of popular movies
     * @param  params page number
     */
    fun getMovies(params: Int?): Flowable<List<Movie>>

    /**
     * Clears all saved popular movies
     */
    fun clearMovies() : Completable

    /**
     * Saves given list of movies
     */
    fun saveMovies(movies: List<Movie>) : Completable
}
