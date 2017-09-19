package com.kiethuynh.kotlinmvp.ui.main

import ch.smartlink.framework.mvpbase.BaseView
import ch.smartlink.framework.mvpbase.core.MVPPresenter

/**
 * Created by kiethuynh on 19/09/2017
 */
interface MainContract {
    interface Presenter: MVPPresenter<View, State>

    interface View: BaseView {
        fun loadingIndicator(enable: Boolean)
    }

    interface State: MVPPresenter.State
}