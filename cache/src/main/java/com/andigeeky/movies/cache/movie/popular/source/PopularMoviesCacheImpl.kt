package com.andigeeky.movies.cache.movie.popular.source

import com.andigeeky.movies.cache.movie.db.MoviesDatabase
import com.andigeeky.movies.cache.movie.popular.model.map
import com.andigeeky.movies.data.movies.popular.model.MovieEntity
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesCache
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class PopularMoviesCacheImpl @Inject constructor(private val database: MoviesDatabase) : PopularMoviesCache{
    override fun getMovies(params: Int?): Flowable<List<MovieEntity>> {
        return database.cachedPopularMoviesDao().getPopularMovies().map {
            it.map { movie -> movie.map() }
        }
    }

    override fun clearMovies(): Completable {
        return database.cachedPopularMoviesDao().clearPopularMovies()
    }

    override fun saveMovies(movies: List<MovieEntity>): Completable {
        return database.cachedPopularMoviesDao()
            .insertPopularMovies(movies.map { it.map() })
    }

}
