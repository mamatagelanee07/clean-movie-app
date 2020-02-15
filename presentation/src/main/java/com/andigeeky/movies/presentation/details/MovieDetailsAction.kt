package com.andigeeky.movies.presentation.details

import com.andigeeky.movies.presentation.common.BaseAction

sealed class MovieDetailsAction : BaseAction {
    data class LoadMovieDetailAction(val id :Int) : MovieDetailsAction()
}
