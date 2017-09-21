package com.kiethuynh.kotlinmvp.ui.profile

import ch.smartlink.framework.mvpbase.BaseView
import ch.smartlink.framework.mvpbase.core.MVPPresenter

/**
 * Created by kiethuynh on 21/09/2017
 */
interface ProfileContract {
    interface Presenter: MVPPresenter<View, State>

    interface View: BaseView

    interface State: MVPPresenter.State
}