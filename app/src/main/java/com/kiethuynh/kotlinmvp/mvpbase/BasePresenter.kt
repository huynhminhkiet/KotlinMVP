package ch.smartlink.framework.mvpbase

import android.widget.Toast
import ch.smartlink.framework.mvpbase.core.MVPPresenter
import com.kiethuynh.kotlinmvp.common.ErrorCallback

/**
 * Created by khanhnguyen on 13/09/2017
 */
abstract class BasePresenter<V : BaseView, S : MVPPresenter.State> : MVPPresenter<V, S>, ErrorCallback {
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

    override fun onTokenInvalidError() {
        view?.showTokenInvalidError()
    }

    override fun onOtherError() {
        view?.showOtherError()
    }

    override fun onNoInternetConnection() {
        view?.showNoInternetConnection()
    }
}