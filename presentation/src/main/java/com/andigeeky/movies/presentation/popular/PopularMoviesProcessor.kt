package com.andigeeky.movies.presentation.popular

import com.andigeeky.movies.domain.movies.popular.usecase.GetPopularMoviesUseCase
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class PopularMoviesProcessor @Inject constructor(
    private val popularMoviesUseCase: GetPopularMoviesUseCase
) {
    private val conversationsProcessor: ObservableTransformer
    <PopularMoviesAction.LoadPopularMoviesAction, PopularMoviesResult> =
        ObservableTransformer {
            it.switchMap {
                popularMoviesUseCase.execute()
                    .map { agencies ->
                        LoadPopularMoviesTask.success(agencies)
                    }
                    .onErrorReturn {
                        LoadPopularMoviesTask.failure()
                    }
                    .toObservable()
                    .startWith(LoadPopularMoviesTask.loading())
            }
        }

    val processor: ObservableTransformer<PopularMoviesAction, PopularMoviesResult>

    init {
        this.processor = ObservableTransformer {
            it.publish { observer ->
                observer.ofType(PopularMoviesAction.LoadPopularMoviesAction::class.java)
                    .compose(conversationsProcessor)
                    .mergeWith(it.filter { action -> action !is PopularMoviesAction.LoadPopularMoviesAction }
                        .flatMap {
                            Observable.error<PopularMoviesResult>(
                                IllegalArgumentException("Unknown Action type")
                            )
                        })
            }
        }
    }
}
