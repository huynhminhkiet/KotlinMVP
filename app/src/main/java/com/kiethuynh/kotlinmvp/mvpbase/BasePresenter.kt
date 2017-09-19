package ch.smartlink.framework.mvpbase

import ch.smartlink.framework.mvpbase.core.MVPPresenter

/**
 * Created by khanhnguyen on 13/09/2017
 */
abstract class BasePresenter<V : BaseView, S : MVPPresenter.State> : MVPPresenter<V, S> {
    protected var view: V? = null
        get() = field

    override fun onAttachView(view: V, state: S?) {
        this.view = view
        onAttachView(state)
    }

    abstract fun onAttachView(state: S?)

    override fun getState(): S? {
        return null
    }
}