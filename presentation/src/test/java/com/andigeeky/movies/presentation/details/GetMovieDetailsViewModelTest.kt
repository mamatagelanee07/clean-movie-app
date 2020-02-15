package com.andigeeky.movies.presentation.details

import com.andigeeky.movies.domain.movies.detail.model.MovieDetails
import com.andigeeky.movies.domain.movies.detail.usecase.GetMovieDetailsUseCase
import com.andigeeky.movies.presentation.test.MoviesFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class GetMovieDetailsViewModelTest {

    @Mock lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var processor: MovieDetailsProcessor

    private var movieId  = 1
    private val movieDetails = MoviesFactory.getMovieDetail(2, 5)

    @Before
    fun setUp() {
        getMovieDetailsUseCase = mock()
        processor = MovieDetailsProcessor(getMovieDetailsUseCase)
        viewModel = MovieDetailsViewModel(processor)

        stubGetMovieDetails(Flowable.just(movieDetails))
    }

    @Test
    fun loadAgenciesIntentReturnsSuccess() {
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(2) { it is MovieDetailsViewState.SUCCESS }
    }

    @Test
    fun loadAgenciesIntentWhenSuccessIsNotInProgress() {
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(2) { !it.loading }
    }

    @Test
    fun loadAgenciesIntentReturnsData() {
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(2) { it.result == movieDetails }
    }

    @Test
    fun loadAgenciesIntentReturnsError() {
        stubGetMovieDetails(Flowable.error(RuntimeException()))
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(2) { it is MovieDetailsViewState.ERROR }
    }

    @Test
    fun loadAgenciesIntentWhenErrorIsNotInProgress() {
        stubGetMovieDetails(Flowable.error(RuntimeException()))
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(2) { !it.loading }
    }

    @Test
    fun loadAgenciesIntentWhenErrorContainsNoData() {
        stubGetMovieDetails(Flowable.error(RuntimeException()))
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(2) { it.result == null }
    }

    @Test
    fun loadAgenciesIntentReturnsLoading() {
        stubGetMovieDetails(Flowable.error(RuntimeException()))
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(1) { it is MovieDetailsViewState.LOADING }
    }

    @Test
    fun loadAgenciesIntentBeginsAsIdle() {
        stubGetMovieDetails(Flowable.error(RuntimeException()))
        val testObserver = viewModel.states().test()
        viewModel.processIntents(Observable.just(MovieDetailsIntent.LoadMovieDetailsIntent(movieId)))
        testObserver.assertValueAt(0) { it is MovieDetailsViewState.IDLE }
    }

    private fun stubGetMovieDetails(movie: Flowable<MovieDetails?>) {
        whenever(getMovieDetailsUseCase.execute(anyOrNull()))
                .thenReturn(movie)
    }

}
