package com.andigeeky.cleanmovieapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}
