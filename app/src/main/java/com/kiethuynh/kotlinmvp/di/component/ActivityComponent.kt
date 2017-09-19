package com.kiethuynh.kotlinmvp.di.component

import com.kiethuynh.kotlinmvp.di.module.ActivityModule
import com.kiethuynh.kotlinmvp.di.scope.ActivityScope
import com.kiethuynh.kotlinmvp.ui.main.MainActivity
import dagger.Component

/**
 * Created by kiethuynh on 14/09/2017.
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}