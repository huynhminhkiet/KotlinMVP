package ch.smartlink.framework.mvpbase

import ch.smartlink.framework.mvpbase.core.MVPFragment
import ch.smartlink.framework.mvpbase.core.MVPPresenter

/**
 * Created by khanhnguyen on 13/09/2017
 */
abstract class BaseFragment<P : MVPPresenter<V, S>, V : BaseView, S : MVPPresenter.State> : MVPFragment<P, V, S>(), BaseView {
    override fun hideKeyboard() {
        (activity as BaseView).hideKeyboard()
    }
}