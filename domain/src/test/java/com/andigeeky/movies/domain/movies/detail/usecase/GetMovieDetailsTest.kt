package com.andigeeky.movies.domain.movies.detail.usecase

import com.andigeeky.movies.domain.executor.PostExecutionThread
import com.andigeeky.movies.domain.executor.ThreadExecutor
import com.andigeeky.movies.domain.movies.detail.repository.MovieDetailsRepository
import com.andigeeky.movies.domain.movies.popular.repository.PopularMoviesRepository
import com.andigeeky.movies.domain.test.MoviesFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class GetMovieDetailsTest {

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var repository: MovieDetailsRepository

    private val movieId = 2
    private val movies = MoviesFactory.getMovieDetails(movieId)

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        repository = mock()
        getMovieDetailsUseCase = GetMovieDetailsUseCase(repository, mockThreadExecutor,
            mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getMovieDetailsUseCase.buildUseCaseObservable(movieId)
        verify(repository).getMovieDetails(movieId)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubPopularMoviesRepositoryGetPopularMovies()
        val testObserver = getMovieDetailsUseCase.buildUseCaseObservable(movieId).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        stubPopularMoviesRepositoryGetPopularMovies()
        val testObserver = getMovieDetailsUseCase.buildUseCaseObservable(movieId).test()
        testObserver.assertValue(movies)
    }

    private fun stubPopularMoviesRepositoryGetPopularMovies() {
        whenever(repository.getMovieDetails(movieId))
            .thenReturn(Flowable.just(movies))
    }
}
