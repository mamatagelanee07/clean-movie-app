package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.presentation.common.BaseResult

sealed class PopularMoviesResult : BaseResult

class LoadPopularMoviesTask(val status : TaskStatus,
                            val popularMovies: List<Movie> = emptyList()) : PopularMoviesResult(){
    companion object{
        internal fun success(popularMovies: List<Movie>): LoadPopularMoviesTask {
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
