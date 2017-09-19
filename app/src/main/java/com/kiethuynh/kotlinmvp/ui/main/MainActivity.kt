package com.kiethuynh.kotlinmvp.ui.main

import android.os.Bundle
import ch.smartlink.framework.mvpbase.BaseActivity
import com.kiethuynh.kotlinmvp.R
import com.kiethuynh.kotlinmvp.utils.hide
import com.kiethuynh.kotlinmvp.utils.show
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by kiethuynh on 19/09/2017
 */
class MainActivity: BaseActivity<MainContract.Presenter, MainContract.View, MainContract.State>(), MainContract.View {
    @Inject lateinit var mainPresenter: Lazy<MainContract.Presenter>

    override fun onCreatePresenter(): MainContract.Presenter? = mainPresenter.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent?.inject(this)
    }

    override fun loadingIndicator(enable: Boolean) {
        if (enable)
            progressBar.show()
        else
            progressBar.hide()
    }
}