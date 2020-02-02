package com.andigeeky.cleanmovieapp.di

import android.app.Application
import com.andigeeky.cleanmovieapp.MoviesApplication
import com.andigeeky.cleanmovieapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        CacheModule::class,
        DataModule::class,
        PresentationModule::class,
        RemoteModule::class,
        ActivitiesModule::class,
        FragmentBuildersModule::class
    ]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MoviesApplication)

}
