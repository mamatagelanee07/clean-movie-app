package com.andigeeky.movies.data.movies.details.source

import com.andigeeky.movies.data.movies.details.model.MovieDetailsEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface MovieDetailsCache {

    fun getMovieDetails(id: Int?): Flowable<MovieDetailsEntity?>

    fun clearMovieDetails() : Completable

    fun saveMovieDetails(movieDetails: MovieDetailsEntity?) : Completable
}
