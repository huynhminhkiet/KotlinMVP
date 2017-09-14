package com.kiethuynh.kotlinmvp

import android.app.Application
import com.kiethuynh.kotlinmvp.di.component.AppComponent
import com.kiethuynh.kotlinmvp.di.component.DaggerAppComponent
import com.kiethuynh.kotlinmvp.di.module.AppModule

/**
 * Created by kiethuynh on 14/09/2017.
 */
class MyApplication: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun getAppComponent() = appComponent
}