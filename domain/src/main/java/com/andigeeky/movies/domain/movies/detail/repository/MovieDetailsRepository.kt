package com.andigeeky.movies.domain.movies.detail.repository

import com.andigeeky.movies.domain.movies.detail.model.MovieDetails
import io.reactivex.Completable
import io.reactivex.Flowable

interface MovieDetailsRepository {

    fun getMovieDetails(id: Int?): Flowable<MovieDetails?>

    fun clearMovieDetails() : Completable

    fun saveMovieDetails(details: MovieDetails?) : Completable
}
