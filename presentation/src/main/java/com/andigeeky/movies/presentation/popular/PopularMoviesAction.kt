package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.presentation.common.BaseAction

sealed class PopularMoviesAction : BaseAction {
    open class LoadPopularMoviesAction(val pageNumber :Int) : PopularMoviesAction()
    class LoadNextPagePopularMoviesAction(pageNumber: Int) : LoadPopularMoviesAction(pageNumber)
}
