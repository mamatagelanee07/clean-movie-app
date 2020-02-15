package com.andigeeky.movies.presentation.details

import com.andigeeky.movies.domain.movies.detail.model.MovieDetails
import com.andigeeky.movies.presentation.common.ViewState

sealed class MovieDetailsViewState(
    loading: Boolean = false,
    error: MovieDetailsError.LoadMovieDetailsError? = null,
    result: MovieDetails? = null) :
    ViewState<MovieDetailsError, MovieDetails?>(loading,error,result){

    object LOADING : MovieDetailsViewState(true)

    object IDLE : MovieDetailsViewState(false)

    data class SUCCESS(val popularMovies: MovieDetails?) :
        MovieDetailsViewState(result = popularMovies)

    data class ERROR(val errorType: MovieDetailsError.LoadMovieDetailsError) :
        MovieDetailsViewState(error = errorType)
}
