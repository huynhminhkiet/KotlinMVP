package com.kiethuynh.kotlinmvp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kiethuynh.kotlinmvp.MyApplication
import com.kiethuynh.kotlinmvp.di.component.ActivityComponent
import com.kiethuynh.kotlinmvp.di.component.DaggerActivityComponent
import com.kiethuynh.kotlinmvp.di.module.ActivityModule

/**
 * Created by kiethuynh on 14/09/2017.
 */
open class BaseActivity: AppCompatActivity() {

    protected var activityComponent: ActivityComponent? = null
        get() = field

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as MyApplication).getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
    }
}