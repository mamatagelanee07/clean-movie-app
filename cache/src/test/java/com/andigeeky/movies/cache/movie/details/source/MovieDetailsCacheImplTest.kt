package com.andigeeky.movies.cache.movie.details.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.andigeeky.movies.cache.movie.MoviesFactory
import com.andigeeky.movies.cache.movie.db.MoviesDatabase
import com.andigeeky.movies.data.movies.details.source.MovieDetailsCache
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class MovieDetailsCacheImplTest {

    private lateinit var cacheImpl: MovieDetailsCache
    private lateinit var database: MoviesDatabase

    private val movieId = 2
    private val itemSize = 2
    private val movieEntities = MoviesFactory.getMovieDetailEntity(movieId, itemSize)

    @JvmField @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application,
            MoviesDatabase::class.java
        ).allowMainThreadQueries().build()

        cacheImpl = MovieDetailsCacheImpl(database)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = cacheImpl.clearMovieDetails().test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetMoviesReturnsMovies() {
        cacheImpl.saveMovieDetails(movieEntities).blockingAwait()
        val testObserver = cacheImpl.getMovieDetails(movieId).test()
        testObserver.assertValue(movieEntities)
    }

}
