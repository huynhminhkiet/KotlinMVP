package com.kiethuynh.kotlinmvp.ui

import android.os.Bundle
import com.kiethuynh.kotlinmvp.R
import com.kiethuynh.kotlinmvp.base.BaseActivity
import com.kiethuynh.kotlinmvp.utils.hide
import com.kiethuynh.kotlinmvp.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var mPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent?.inject(this)

        var state: MainContract.State? = null

        savedInstanceState?.apply {
            state = getParcelable("abc")
        }

        mPresenter.bind(this, state)

        btnStart.setOnClickListener { _ -> mPresenter.onStartButtonClick() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putParcelable("abc" ,mPresenter.getState())
    }

    override fun hideProgressBar() {
        progressBar.hide()
    }

    override fun showProgressBar() {
        progressBar.show()
    }

    override fun updateNumber(number: Int) {
        tvNumber.text = number.toString()
    }
}
