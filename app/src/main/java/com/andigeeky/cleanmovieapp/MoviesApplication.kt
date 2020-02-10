package com.andigeeky.cleanmovieapp

import android.app.Application
import android.widget.Toast
import com.andigeeky.cleanmovieapp.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class MoviesApplication : Application(), HasAndroidInjector{

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        setupTimber()
//        setupRxErrorHandler()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { error: Throwable? ->
            when(error){
                is IOException -> Toast.makeText(
                    this, "There is no internet connection. Please try again later", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    override fun androidInjector() = androidInjector
}
