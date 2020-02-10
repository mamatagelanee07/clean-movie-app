package com.andigeeky.movies.domain.movies.detail.usecase

import com.andigeeky.movies.domain.executor.PostExecutionThread
import com.andigeeky.movies.domain.executor.ThreadExecutor
import com.andigeeky.movies.domain.interactor.FlowableUseCase
import com.andigeeky.movies.domain.movies.detail.model.MovieDetails
import com.andigeeky.movies.domain.movies.detail.repository.MovieDetailsRepository
import com.andigeeky.movies.domain.movies.popular.model.PopularMovies
import com.andigeeky.movies.domain.movies.popular.repository.PopularMoviesRepository
import io.reactivex.Flowable
import javax.inject.Inject

open class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<MovieDetails?, Int>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Int?): Flowable<MovieDetails?> {
        return movieDetailsRepository.getMovieDetails(params)
    }
}
