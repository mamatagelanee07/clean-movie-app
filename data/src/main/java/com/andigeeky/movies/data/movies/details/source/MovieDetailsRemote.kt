package com.andigeeky.movies.data.movies.details.source

import com.andigeeky.movies.data.movies.details.model.MovieDetailsEntity
import io.reactivex.Flowable

interface MovieDetailsRemote{
    fun getMovieDetails(id : Int?) : Flowable<MovieDetailsEntity?>?
}
