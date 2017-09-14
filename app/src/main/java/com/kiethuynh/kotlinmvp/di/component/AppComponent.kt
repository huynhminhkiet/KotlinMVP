package com.kiethuynh.kotlinmvp.di.component

import com.kiethuynh.kotlinmvp.di.module.AppModule
import com.kiethuynh.kotlinmvp.di.scope.ApplicationScope
import dagger.Component

/**
 * Created by kiethuynh on 14/09/2017.
 */

@ApplicationScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

}