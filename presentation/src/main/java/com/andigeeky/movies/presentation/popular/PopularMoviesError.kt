package com.andigeeky.movies.presentation.popular

sealed class PopularMoviesError {
    object LoadPopularMoviesError : PopularMoviesError()
}
