package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.presentation.common.BaseAction

sealed class PopularMoviesAction : BaseAction {
    object LoadPopularMoviesAction : PopularMoviesAction()
}
