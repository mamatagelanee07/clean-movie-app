package com.andigeeky.cleanmovieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.andigeeky.cleanmovieapp.databinding.FragmentPopularMovieBinding
import com.andigeeky.cleanmovieapp.di.Injectable
import com.andigeeky.cleanmovieapp.ui.common.FragmentDataBindingComponent
import com.andigeeky.cleanmovieapp.ui.common.autoCleared
import com.andigeeky.movies.data.executor.JobExecutor
import com.andigeeky.movies.presentation.common.BaseView
import com.andigeeky.movies.presentation.popular.PopularMoviesIntent
import com.andigeeky.movies.presentation.popular.PopularMoviesViewModel
import com.andigeeky.movies.presentation.popular.PopularMoviesViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

/**
 * The UI Controller for displaying all Lines information of TFL.
 */
class PopularMoviesFragment : Fragment(), Injectable ,
    BaseView<PopularMoviesIntent, PopularMoviesViewState> {

    @Inject
    lateinit var appExecutors: JobExecutor

    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var adapter by autoCleared<PopularMoviesAdapter>()
    private var binding by autoCleared<FragmentPopularMovieBinding>()

    private val compositeDisposable = CompositeDisposable()
    private val refreshIntent =
        BehaviorSubject.create<PopularMoviesIntent.RefreshPopularMoviesIntent>()
    private val initialIntent = Observable
        .just(PopularMoviesIntent.LoadPopularMoviesIntent)

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_popular_movie,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this, factory)
            .get(PopularMoviesViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        this.adapter = PopularMoviesAdapter(
            dataBindingComponent,
            appExecutors
        ) {
            Timber.e("Clicked $it")
        }
        binding.listLines.adapter = adapter

        compositeDisposable.add(viewModel.states().subscribe { render(it) })
        viewModel.processIntents(intents())
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun intents(): Observable<PopularMoviesIntent> {
        return Observable.merge(initialIntent,
            refreshIntent)
    }

    override fun render(state: PopularMoviesViewState) {
        when {
            state.loading -> {
                makeToast("loading")
            }
            state is PopularMoviesViewState.ERROR -> {
                makeToast("error")
            }
            state is PopularMoviesViewState.SUCCESS -> {
                makeToast("Success")
                Log.e("Popular Movies : " , state.popularMovies.toString())
                val lines = state.popularMovies
                adapter.submitList(lines)
            }
        }
    }

    private fun makeToast(message: String){
        Toast.makeText(activity,message, Toast.LENGTH_LONG).show()
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}
