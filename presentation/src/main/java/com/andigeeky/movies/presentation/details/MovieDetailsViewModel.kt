package com.andigeeky.movies.presentation.details

import androidx.lifecycle.ViewModel
import com.andigeeky.movies.presentation.common.BaseViewModel
import com.andigeeky.movies.presentation.common.FAILURE
import com.andigeeky.movies.presentation.common.LOADING
import com.andigeeky.movies.presentation.common.SUCCESS
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MovieDetailsViewModel @Inject internal constructor(
    private val movieDetailsProcessor: MovieDetailsProcessor
) : ViewModel(), BaseViewModel<MovieDetailsIntent, MovieDetailsViewState> {

    private val intentsSubject: PublishSubject<MovieDetailsIntent> = PublishSubject.create()
    private val statesSubject: Observable<MovieDetailsViewState> = compose()

    override fun processIntents(intents: Observable<MovieDetailsIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<MovieDetailsViewState> {
        return statesSubject
    }

    private fun getIntentFilter(): ObservableTransformer<MovieDetailsIntent, MovieDetailsIntent> {
        return ObservableTransformer {
            it.publish { observable ->
                Observable.merge(observable.ofType(MovieDetailsIntent.LoadMovieDetailsIntent::class.java).take(1),
                    observable.filter { intent -> intent !is MovieDetailsIntent.LoadMovieDetailsIntent })
            }
        }
    }

    private fun getReducer(): BiFunction<MovieDetailsViewState, MovieDetailsResult, MovieDetailsViewState> {
        return BiFunction { _, result ->
            when (result) {
                is LoadMovieDetailsTask -> {
                    when (result.status) {
                        SUCCESS -> MovieDetailsViewState.SUCCESS(
                            result.movieDetails
                        )
                        FAILURE -> MovieDetailsViewState.ERROR(MovieDetailsError.LoadMovieDetailsError)
                        LOADING -> MovieDetailsViewState.LOADING
                    }
                }
            }
        }
    }

    private fun compose(): Observable<MovieDetailsViewState> {
        return intentsSubject
            .compose(getIntentFilter())
            .map { this.actionFromIntent(it) }
            .compose(movieDetailsProcessor.processor)
            .scan(MovieDetailsViewState.IDLE, getReducer())
            .replay(1)
            .autoConnect(0)
    }

    private fun actionFromIntent(intent: MovieDetailsIntent): MovieDetailsAction {
        return when (intent) {
            is MovieDetailsIntent.LoadMovieDetailsIntent -> MovieDetailsAction.LoadMovieDetailAction(intent.id)
        }
    }
}
