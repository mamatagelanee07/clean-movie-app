package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.presentation.common.BaseIntent

sealed class PopularMoviesIntent : BaseIntent {
    data class LoadPopularMoviesIntent(val pageNumber : Int = 1) : PopularMoviesIntent()
    data class LoadNextPopularMoviesIntent(val pageNumber : Int = 1) : PopularMoviesIntent()
}
