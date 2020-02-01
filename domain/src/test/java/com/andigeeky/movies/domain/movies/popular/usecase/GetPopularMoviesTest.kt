package com.andigeeky.movies.domain.movies.popular.usecase

import com.andigeeky.movies.domain.executor.PostExecutionThread
import com.andigeeky.movies.domain.executor.ThreadExecutor
import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.domain.movies.popular.repository.PopularMoviesRepository
import com.andigeeky.movies.domain.test.MoviesFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class GetPopularMoviesTest {

    private lateinit var popularMoviesUseCase: GetPopularMoviesUseCase

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var repository: PopularMoviesRepository
    private val pageNumber = 1
    private val movieCount = 2

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        repository = mock()
        popularMoviesUseCase = GetPopularMoviesUseCase(repository, mockThreadExecutor,
            mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        popularMoviesUseCase.buildUseCaseObservable(pageNumber)
        verify(repository).getMovies(pageNumber)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubPopularMoviesRepositoryGetPopularMovies(MoviesFactory.getMovies(movieCount))
        val testObserver = popularMoviesUseCase.buildUseCaseObservable(pageNumber).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val agencies = MoviesFactory.getMovies(movieCount)
        stubPopularMoviesRepositoryGetPopularMovies(agencies)
        val testObserver = popularMoviesUseCase.buildUseCaseObservable(pageNumber).test()
        testObserver.assertValue(agencies)
    }

    private fun stubPopularMoviesRepositoryGetPopularMovies(movies: List<Movie>) {
        whenever(repository.getMovies(1))
            .thenReturn(Flowable.just(movies))
    }
}
