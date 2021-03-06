package com.andigeeky.cleanmovieapp.di.modules

import com.andigeeky.cleanmovieapp.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePopularMoviesFragment(): PopularMoviesFragment
}
