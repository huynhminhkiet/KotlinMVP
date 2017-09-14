package com.kiethuynh.kotlinmvp.di.module

import android.app.Application
import com.kiethuynh.kotlinmvp.di.qualifier.ApplicationContext
import com.kiethuynh.kotlinmvp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by kiethuynh on 14/09/2017.
 */

@Module
class AppModule(private val application: Application) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext() = application.applicationContext
}