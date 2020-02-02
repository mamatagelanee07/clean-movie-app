package com.andigeeky.cleanmovieapp.di.modules

import com.andigeeky.cleanmovieapp.MainActivity
import com.andigeeky.cleanmovieapp.MainThread
import com.andigeeky.movies.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module that provides all dependencies from the mobile-ui package/layer and injects all activities.
 */
@Module
abstract class ActivitiesModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: MainThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
