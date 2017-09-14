package com.kiethuynh.kotlinmvp.ui

import javax.inject.Inject

/**
 * Created by kiethuynh on 14/09/2017.
 */
class MainPresenter @Inject constructor() : MainContract.Presenter {
    private var view: MainContract.View? = null

    private var number: Int = 0

    override fun onStartButtonClick() {
        view?.updateNumber(++number)
    }

    override fun bind(view: MainContract.View?, state: MainContract.State?) {
        this.view = view
        if (state != null)
            number = state.getNumber()

        this.view?.hideProgressBar()
        view?.updateNumber(number)
    }

    override fun getState(): MainContract.State {
        return MainState(number)
    }
}