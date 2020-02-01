package com.andigeeky.movies.data.movies.popular.source

import com.andigeeky.movies.data.movies.popular.model.MovieRemote
import io.reactivex.Flowable

interface PopularMoviesRemote{
    fun getMovies(pageNumber : Int?) : Flowable<List<MovieRemote>>
}
