package com.kiethuynh.kotlinmvp.di.module

import android.app.Activity
import android.content.Context
import com.kiethuynh.kotlinmvp.di.qualifier.ActivityContext
import com.kiethuynh.kotlinmvp.di.scope.ActivityScope
import com.kiethuynh.kotlinmvp.ui.MainContract
import com.kiethuynh.kotlinmvp.ui.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by kiethuynh on 14/09/2017.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    @ActivityScope
    fun provideActivity() = activity

    @Provides
    @ActivityScope
    fun provideMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter = mainPresenter
}