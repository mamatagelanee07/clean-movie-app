package com.andigeeky.movies.presentation.details

import com.andigeeky.movies.domain.movies.detail.model.MovieDetails
import com.andigeeky.movies.presentation.common.*

sealed class MovieDetailsResult : BaseResult

class LoadMovieDetailsTask(val status : TaskStatus,
                            val movieDetails: MovieDetails? = null) : MovieDetailsResult(){
    companion object{
        internal fun success(popularMovies: MovieDetails?): LoadMovieDetailsTask {
            return LoadMovieDetailsTask(SUCCESS, popularMovies)
        }

        internal fun failure(): LoadMovieDetailsTask {
            return LoadMovieDetailsTask(FAILURE)
        }

        internal fun loading(): LoadMovieDetailsTask {
            return LoadMovieDetailsTask(LOADING)
        }
    }
}
