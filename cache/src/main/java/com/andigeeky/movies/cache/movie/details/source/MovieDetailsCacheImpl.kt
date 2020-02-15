package com.andigeeky.movies.cache.movie.details.source

import com.andigeeky.movies.cache.movie.db.MoviesDatabase
import com.andigeeky.movies.cache.movie.details.model.map
import com.andigeeky.movies.data.movies.details.model.MovieDetailsEntity
import com.andigeeky.movies.data.movies.details.source.MovieDetailsCache
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class MovieDetailsCacheImpl @Inject constructor(private val database: MoviesDatabase) :
    MovieDetailsCache {
    override fun getMovieDetails(id: Int?): Flowable<MovieDetailsEntity?> {
        return database.cachedMovieDetailDao().getMovieDetails(id).map { it.map() }
    }

    override fun clearMovieDetails(): Completable {
        return database.cachedMovieDetailDao().clearMovieDetails()
    }

    override fun saveMovieDetails(movieDetails: MovieDetailsEntity?): Completable {
        return Completable.fromAction {
            database.cachedMovieDetailDao()
                .insertMovieDetails(movieDetails?.map())
        }
    }
}
