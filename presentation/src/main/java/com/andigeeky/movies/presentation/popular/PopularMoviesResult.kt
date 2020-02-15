package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.domain.movies.popular.model.PopularMovies
import com.andigeeky.movies.presentation.common.*

sealed class PopularMoviesResult : BaseResult

class LoadPopularMoviesTask(val status : TaskStatus,
                            val popularMovies: PopularMovies? = null) : PopularMoviesResult(){
    companion object{
        internal fun success(popularMovies: PopularMovies?): LoadPopularMoviesTask {
            return LoadPopularMoviesTask(SUCCESS, popularMovies)
        }

        internal fun failure(): LoadPopularMoviesTask {
            return LoadPopularMoviesTask(FAILURE)
        }

        internal fun loading(): LoadPopularMoviesTask {
            return LoadPopularMoviesTask(LOADING)
        }
    }
}
