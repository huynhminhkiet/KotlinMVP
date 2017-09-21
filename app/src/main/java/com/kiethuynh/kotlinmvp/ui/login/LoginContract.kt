package com.kiethuynh.kotlinmvp.ui.login

import ch.smartlink.framework.mvpbase.BaseView
import ch.smartlink.framework.mvpbase.core.MVPPresenter

/**
 * Created by kiethuynh on 19/09/2017
 */
interface LoginContract {
    interface Presenter: MVPPresenter<View, State> {
        fun onLoginButtonClick(username: String, password: String)
    }

    interface View: BaseView {
        fun loadingIndicator(enable: Boolean)
        fun goToProfileScreen()
    }

    interface State: MVPPresenter.State
}