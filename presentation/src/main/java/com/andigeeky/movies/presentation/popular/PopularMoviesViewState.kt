package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.presentation.common.ViewState

sealed class PopularMoviesViewState(
    loading: Boolean = false,
    error: PopularMoviesError.LoadPopularMoviesError? = null,
    result: List<Movie> = emptyList()) :
    ViewState<PopularMoviesError, List<Movie>>(loading,error,result){

    object LOADING : PopularMoviesViewState(true)

    object IDLE : PopularMoviesViewState(false)

    data class SUCCESS(val popularMovies: List<Movie>) :
        PopularMoviesViewState(result = popularMovies)

    data class ERROR(val errorType: PopularMoviesError.LoadPopularMoviesError) :
        PopularMoviesViewState(error = errorType)
}
