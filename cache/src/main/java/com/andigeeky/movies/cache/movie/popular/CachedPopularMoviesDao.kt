package com.andigeeky.movies.cache.movie.popular

import androidx.room.*
import com.andigeeky.movies.cache.movie.popular.model.CachedMovie
import com.andigeeky.movies.cache.movie.popular.model.CachedMoviePages
import com.andigeeky.movies.cache.movie.popular.model.CachedPageWithMovies
import com.andigeeky.movies.cache.movie.popular.model.CachedPopularMovies
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CachedPopularMoviesDao {

    @Transaction
    @Query("select * from CachedMoviePages where page = :pageNumber")
    fun getPopularMovies(pageNumber : Int?): Single<CachedPopularMovies?>

    @Query("Delete from CachedMoviePages")
    fun clearPopularMovies(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<CachedMovie?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPages(movies: CachedMoviePages?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPageWithMovies(movies: CachedPageWithMovies?)

    @Transaction
    fun insertPopularMovies(
        movies: CachedPopularMovies?
    ) {
        insertPages(movies?.page)
        insertMovies(movies?.results)
        movies?.results?.forEach {
            it?.let { movie ->
                insertPageWithMovies(
                    CachedPageWithMovies(
                        pageId = movies.page.page,
                        movieId = movie.id
                    )
                )
            }
        }
    }
}
