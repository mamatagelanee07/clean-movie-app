package com.andigeeky.movies.remote.movies

import com.andigeeky.movies.remote.movies.popular.model.PopularMovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface MoviesService {

    @GET("agency")
    fun getAgencies(): Flowable<PopularMovieResponse>
}
