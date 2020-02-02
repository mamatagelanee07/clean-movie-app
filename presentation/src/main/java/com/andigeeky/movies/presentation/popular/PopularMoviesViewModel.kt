package com.andigeeky.movies.presentation.popular

import androidx.lifecycle.ViewModel
import com.andigeeky.movies.presentation.common.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class PopularMoviesViewModel @Inject internal constructor(
    private val agenciesProcessor: PopularMoviesProcessor
) : ViewModel(), BaseViewModel<PopularMoviesIntent, PopularMoviesViewState> {

    private val intentsSubject: PublishSubject<PopularMoviesIntent> = PublishSubject.create()
    private val statesSubject: Observable<PopularMoviesViewState> = compose()

    override fun processIntents(intents: Observable<PopularMoviesIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<PopularMoviesViewState> {
        return statesSubject
    }

    private fun getIntentFilter(): ObservableTransformer<PopularMoviesIntent, PopularMoviesIntent> {
        return ObservableTransformer {
            it.publish { observable ->
                Observable.merge(observable.ofType(PopularMoviesIntent.LoadPopularMoviesIntent::class.java).take(1),
                    observable.filter { intent -> intent !is PopularMoviesIntent.LoadPopularMoviesIntent })
            }
        }
    }

    private fun getReducer(): BiFunction<PopularMoviesViewState, PopularMoviesResult, PopularMoviesViewState> {
        return BiFunction { _, result ->
            when (result) {
                is LoadPopularMoviesTask -> {
                    when (result.status) {
                        SUCCESS -> PopularMoviesViewState.SUCCESS(
                            result.popularMovies
                        )
                        FAILURE -> PopularMoviesViewState.ERROR(PopularMoviesError.LoadPopularMoviesError)
                        LOADING -> PopularMoviesViewState.LOADING
                    }
                }
            }
        }
    }

    private fun compose(): Observable<PopularMoviesViewState> {
        return intentsSubject
            .compose(getIntentFilter())
            .map { this.actionFromIntent(it) }
            .compose(agenciesProcessor.processor)
            .scan(PopularMoviesViewState.IDLE, getReducer())
            .replay(1)
            .autoConnect(0)
    }

    private fun actionFromIntent(intent: PopularMoviesIntent): PopularMoviesAction {
        return when (intent) {
            is PopularMoviesIntent.LoadPopularMoviesIntent -> PopularMoviesAction.LoadPopularMoviesAction
            PopularMoviesIntent.RefreshPopularMoviesIntent -> PopularMoviesAction.LoadPopularMoviesAction
        }
    }
}
