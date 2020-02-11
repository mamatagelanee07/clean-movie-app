package com.andigeeky.movies.remote.movies

import com.andigeeky.movies.remote.movies.details.model.MovieDetailsResponse
import com.andigeeky.movies.remote.movies.popular.model.PopularMovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface MoviesService {

    @GET("movie/popular?api_key=fd1a1f8dfea7db3b2efc83939f21ec33")
    fun getPopularMovies(@Query(value="page", encoded=true) pageNumber: Int?): Flowable<PopularMovieResponse?>?

    @GET("movie/{id}?api_key=fd1a1f8dfea7db3b2efc83939f21ec33")
    fun getMovieDetails(@Path("id") id: Int?): Flowable<MovieDetailsResponse?>?
}
