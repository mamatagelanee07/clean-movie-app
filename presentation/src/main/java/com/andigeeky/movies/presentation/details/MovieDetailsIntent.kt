package com.andigeeky.movies.presentation.details

import com.andigeeky.movies.presentation.common.BaseIntent

sealed class MovieDetailsIntent : BaseIntent {
    data class LoadMovieDetailsIntent(val id : Int) : MovieDetailsIntent()
}
