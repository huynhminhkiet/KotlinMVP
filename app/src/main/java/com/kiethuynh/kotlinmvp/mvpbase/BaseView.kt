package ch.smartlink.framework.mvpbase

import ch.smartlink.framework.mvpbase.core.MVPPresenter

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface BaseView : MVPPresenter.View {
    fun hideKeyboard()

    fun setLoadingIndicator(visible : Boolean)
    fun showTokenInvalidError()
    fun showOtherError()
    fun showNoInternetConnection()
    fun showUnauthorizedError()
    fun navigateLoginScreen()
}