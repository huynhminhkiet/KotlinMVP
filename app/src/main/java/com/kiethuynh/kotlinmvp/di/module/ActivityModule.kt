package com.kiethuynh.kotlinmvp.di.module

import android.app.Activity
import android.content.Context
import com.kiethuynh.kotlinmvp.di.qualifier.ActivityContext
import com.kiethuynh.kotlinmvp.di.scope.ActivityScope
import com.kiethuynh.kotlinmvp.ui.login.LoginContract
import com.kiethuynh.kotlinmvp.ui.login.LoginPresenter
import com.kiethuynh.kotlinmvp.ui.profile.ProfileContract
import com.kiethuynh.kotlinmvp.ui.profile.ProfilePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by kiethuynh on 14/09/2017
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    @ActivityScope
    fun provideActivity() = activity

    @Provides
    @ActivityScope
    fun provideMainPresenter(mainPresenter: LoginPresenter): LoginContract.Presenter = mainPresenter

    @Provides
    @ActivityScope
    fun provideProfilePresenter(profilePresenter: ProfilePresenter): ProfileContract.Presenter = profilePresenter
}