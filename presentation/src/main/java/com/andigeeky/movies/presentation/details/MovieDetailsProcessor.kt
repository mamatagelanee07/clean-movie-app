package com.andigeeky.movies.presentation.details

import com.andigeeky.movies.domain.movies.detail.usecase.GetMovieDetailsUseCase
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MovieDetailsProcessor @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase
) {
    private val conversationsProcessor: ObservableTransformer
    <MovieDetailsAction.LoadMovieDetailAction, MovieDetailsResult> =
        ObservableTransformer {
            it.switchMap {action ->
                movieDetailsUseCase.execute(action.id)
                    .map { agencies ->
                        LoadMovieDetailsTask.success(agencies)
                    }
                    .onErrorReturn {
                        LoadMovieDetailsTask.failure()
                    }
                    .toObservable()
                    .startWith(LoadMovieDetailsTask.loading())
            }
        }

    val processor: ObservableTransformer<MovieDetailsAction, MovieDetailsResult>

    init {
        this.processor = ObservableTransformer {
            it.publish { observer ->
                observer.ofType(MovieDetailsAction.LoadMovieDetailAction::class.java)
                    .compose(conversationsProcessor)
                    .mergeWith(it.filter { action -> action !is MovieDetailsAction.LoadMovieDetailAction }
                        .flatMap {
                            Observable.error<MovieDetailsResult>(
                                IllegalArgumentException("Unknown Action type")
                            )
                        })
            }
        }
    }
}
