package com.andigeeky.movies.data.movies.popular.source

import com.andigeeky.movies.data.movies.popular.model.MovieEntity
import io.reactivex.Flowable

interface PopularMoviesRemote{
    fun getMovies(pageNumber : Int?) : Flowable<List<MovieEntity>>
}
