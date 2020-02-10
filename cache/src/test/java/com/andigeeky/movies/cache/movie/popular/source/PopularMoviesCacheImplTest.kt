package com.andigeeky.movies.cache.movie.popular.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.andigeeky.movies.cache.movie.MoviesFactory
import com.andigeeky.movies.cache.movie.db.MoviesDatabase
import com.andigeeky.movies.cache.movie.popular.model.CachedMoviePages
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesCache
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class PopularMoviesCacheImplTest {

    private lateinit var cacheImpl: PopularMoviesCache
    private lateinit var database: MoviesDatabase

    private val pageNumbers = 2
    private val movieNumbers = 2
    private val movieEntities = MoviesFactory.getPopularMoviesEntity(pageNumbers, movieNumbers)

    @JvmField @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application,
            MoviesDatabase::class.java
        ).allowMainThreadQueries().build()

        cacheImpl = PopularMoviesCacheImpl(database)
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = cacheImpl.clearMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetMoviesReturnsMovies() {
        cacheImpl.saveMovies(movieEntities).blockingAwait()
        val testObserver = cacheImpl.getMovies(pageNumbers).test()
        testObserver.assertValue(movieEntities)
    }

}
