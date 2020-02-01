package com.andigeeky.movies.cache.movie.popular

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andigeeky.movies.cache.movie.db.MovieDBQueries
import com.andigeeky.movies.cache.movie.popular.model.CachedMovie
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CachedPopularMoviesDao {

    @Query(MovieDBQueries.QUERY_POPULAR_MOVIES)
    fun getPopularMovies(): Flowable<List<CachedMovie>>

    @Query(MovieDBQueries.DELETE_POPULAR_MOVIES)
    fun clearPopularMovies() : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movies: List<CachedMovie>): Completable

}
