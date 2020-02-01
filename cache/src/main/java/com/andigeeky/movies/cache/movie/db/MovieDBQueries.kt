package com.andigeeky.movies.cache.movie.db

object MovieDBQueries {

    const val TABLE_POPULAR_MOVIES = "popular_movies"

    const val QUERY_POPULAR_MOVIES = "select * from $TABLE_POPULAR_MOVIES"
    const val DELETE_POPULAR_MOVIES = "DELETE FROM $TABLE_POPULAR_MOVIES"
}
