package com.kiethuynh.kotlinmvp.ui.profile

import ch.smartlink.framework.mvpbase.BaseView
import ch.smartlink.framework.mvpbase.core.MVPPresenter
import com.kiethuynh.kotlinmvp.data.model.User

/**
 * Created by kiethuynh on 21/09/2017
 */
interface ProfileContract {
    interface Presenter: MVPPresenter<View, State>

    interface View: BaseView {
        fun showUserInfo(user: User)
        fun loadingIndicator(enable: Boolean)
    }

    interface State: MVPPresenter.State
}