package com.andigeeky.movies.presentation.details

sealed class MovieDetailsError {
    object LoadMovieDetailsError : MovieDetailsError()
}
