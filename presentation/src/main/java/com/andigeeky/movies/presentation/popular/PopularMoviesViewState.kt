package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.domain.movies.popular.model.PopularMovies
import com.andigeeky.movies.presentation.common.ViewState

sealed class PopularMoviesViewState(
    loading: Boolean = false,
    error: PopularMoviesError.LoadPopularMoviesError? = null,
    result: PopularMovies? = null) :
    ViewState<PopularMoviesError, PopularMovies?>(loading,error,result){

    object LOADING : PopularMoviesViewState(true)

    object IDLE : PopularMoviesViewState(false)

    data class SUCCESS(val popularMovies: PopularMovies?) :
        PopularMoviesViewState(result = popularMovies)

    data class ERROR(val errorType: PopularMoviesError.LoadPopularMoviesError) :
        PopularMoviesViewState(error = errorType)
}
