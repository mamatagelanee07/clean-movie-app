package com.andigeeky.movies.cache.movie.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.andigeeky.movies.cache.movie.popular.CachedPopularMoviesDao
import com.andigeeky.movies.cache.movie.popular.model.CachedMovie
import com.andigeeky.movies.cache.movie.popular.model.CachedMoviePages
import com.andigeeky.movies.cache.movie.popular.model.CachedPageWithMovies
import javax.inject.Inject

@Database(entities = [
    CachedMovie::class,
    CachedMoviePages::class,
    CachedPageWithMovies::class
], version = 1)
abstract class MoviesDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedPopularMoviesDao(): CachedPopularMoviesDao

    companion object{
        const val NAME = "movies.db"
    }
}
