package com.andigeeky.movies.data.movies.popular.repository

import com.andigeeky.movies.data.movies.MoviesFactory
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesCache
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesDataFactory
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesRemote
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PopularMoviesDataRepositoryTest {

    private lateinit var popularMoviesDataRepository: PopularMoviesDataRepository
    private lateinit var popularMoviesDataFactory: PopularMoviesDataFactory
    private lateinit var popularMoviesCache: PopularMoviesCache
    private lateinit var popularMoviesRemote: PopularMoviesRemote
    private val pageNumber = 1
    private val moviesNumber = 2
    private val movies = MoviesFactory.getMovies(moviesNumber)
    private val movieEntities = MoviesFactory.getMoviesEntity(moviesNumber)

    @Before
    fun setUp(){
        popularMoviesDataFactory = mock()
        popularMoviesCache = mock()
        popularMoviesRemote = mock()

        popularMoviesDataRepository = PopularMoviesDataRepository(
            popularMoviesDataFactory
        )

        whenever(popularMoviesDataFactory.retrieveCacheDataStore())
            .thenReturn(popularMoviesCache)
        whenever(popularMoviesDataFactory.retrieveRemoteDataStore())
            .thenReturn(popularMoviesRemote)
        whenever(popularMoviesCache.saveMovies(any()))
            .thenReturn(Completable.complete())
        whenever(popularMoviesCache.clearMovies())
            .thenReturn(Completable.complete())

        whenever(popularMoviesDataFactory.retrieveCacheDataStore().getMovies(pageNumber))
            .thenReturn(Flowable.just(movieEntities))
        whenever(popularMoviesDataFactory.retrieveRemoteDataStore().getMovies(pageNumber))
            .thenReturn(Flowable.just(movieEntities))

    }

    @Test
    fun testGetMoviesReturnsMovies(){
        val testObserver = popularMoviesDataRepository.getMovies(pageNumber).test()
        testObserver.assertValues(movies)
    }

    @Test
    fun testSaveMoviesCompletes() {
        val testObserver = popularMoviesDataRepository.saveMovies(
            movies).test()
        testObserver.assertComplete()
    }

    @Test
    fun testClearMoviesCompletes() {
        val testObserver = popularMoviesDataRepository.clearMovies().test()
        testObserver.assertComplete()
    }
}
