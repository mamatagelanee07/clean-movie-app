package com.andigeeky.cleanmovieapp.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.andigeeky.cleanmovieapp.R
import com.andigeeky.cleanmovieapp.databinding.ItemMovieBinding
import com.andigeeky.cleanmovieapp.common.DataBoundListAdapter
import com.andigeeky.movies.data.executor.JobExecutor
import com.andigeeky.movies.domain.movies.popular.model.Movie

class PopularMoviesAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: JobExecutor,
    private val callback: ((Movie?) -> Unit)?
) : DataBoundListAdapter<Movie?, ItemMovieBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Movie?>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ItemMovieBinding {
        val binding = DataBindingUtil
            .inflate<ItemMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.setOnClickListener {
            binding.movie?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemMovieBinding, item: Movie?) {
        binding.movie = item
    }
}
