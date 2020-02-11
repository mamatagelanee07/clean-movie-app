package com.andigeeky.movies.data.movies.details.repository

import com.andigeeky.movies.data.movies.MoviesFactory
import com.andigeeky.movies.data.movies.details.map
import com.andigeeky.movies.data.movies.details.source.MovieDetailsCache
import com.andigeeky.movies.data.movies.details.source.MovieDetailsDataFactory
import com.andigeeky.movies.data.movies.details.source.MovieDetailsRemote
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
class MovieDetailsDataRepositoryTest {

    private lateinit var movieDetailsDataRepository: MovieDetailsDataRepository
    private lateinit var movieDetailsDataFactory: MovieDetailsDataFactory
    private lateinit var movieDetailsCache: MovieDetailsCache
    private lateinit var movieDetailsRemote: MovieDetailsRemote
    private val id = 1
    private val detail = MoviesFactory.getMovieDetails(id)

    @Before
    fun setUp(){
        movieDetailsDataFactory = mock()
        movieDetailsCache = mock()
        movieDetailsRemote = mock()

        movieDetailsDataRepository = MovieDetailsDataRepository(
            movieDetailsDataFactory
        )

        whenever(movieDetailsDataFactory.retrieveCacheDataStore())
            .thenReturn(movieDetailsCache)
        whenever(movieDetailsDataFactory.retrieveRemoteDataStore())
            .thenReturn(movieDetailsRemote)
        whenever(movieDetailsCache.saveMovieDetails(any()))
            .thenReturn(Completable.complete())
        whenever(movieDetailsCache.clearMovieDetails())
            .thenReturn(Completable.complete())

        whenever(movieDetailsDataFactory.retrieveCacheDataStore().getMovieDetails(id))
            .thenReturn(Flowable.just(detail))
        whenever(movieDetailsDataFactory.retrieveRemoteDataStore().getMovieDetails(id))
            .thenReturn(Flowable.just(detail))

    }

    @Test
    fun testGetMoviesReturnsMovies(){
        val testObserver = movieDetailsDataRepository.getMovieDetails(id).test()
        testObserver.assertValueAt(1, detail.map())
    }

    @Test
    fun testSaveMoviesCompletes() {
        val testObserver = movieDetailsDataRepository.saveMovieDetails(
            detail.map()).test()
        testObserver.assertComplete()
    }

    @Test
    fun testClearMoviesCompletes() {
        val testObserver = movieDetailsDataRepository.clearMovieDetails().test()
        testObserver.assertComplete()
    }
}
