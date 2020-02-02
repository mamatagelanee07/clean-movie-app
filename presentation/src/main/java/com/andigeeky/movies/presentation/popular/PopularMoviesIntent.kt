package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.presentation.common.BaseIntent

sealed class PopularMoviesIntent : BaseIntent {
    object LoadPopularMoviesIntent : PopularMoviesIntent()
    object RefreshPopularMoviesIntent : PopularMoviesIntent()
}
