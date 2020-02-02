package com.andigeeky.movies.domain.movies.popular.usecase

import com.andigeeky.movies.domain.executor.PostExecutionThread
import com.andigeeky.movies.domain.executor.ThreadExecutor
import com.andigeeky.movies.domain.interactor.FlowableUseCase
import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.domain.movies.popular.repository.PopularMoviesRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retrieving a list of [Movie] instances from the [PopularMoviesRepository]
 */
open class GetPopularMoviesUseCase @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<Movie?>?, Int>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Int?): Flowable<List<Movie?>?> {
        return popularMoviesRepository.getMovies(params)
    }
}
