package com.kiethuynh.kotlinmvp.di.component

import com.kiethuynh.kotlinmvp.di.module.ActivityModule
import com.kiethuynh.kotlinmvp.di.scope.ActivityScope
import com.kiethuynh.kotlinmvp.ui.login.LoginActivity
import com.kiethuynh.kotlinmvp.ui.profile.ProfileActivity
import dagger.Component

/**
 * Created by kiethuynh on 14/09/2017
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun inject(mainActivity: LoginActivity)
    fun inject(profileActivity: ProfileActivity)
}