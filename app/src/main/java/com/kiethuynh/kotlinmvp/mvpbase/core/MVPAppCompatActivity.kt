package ch.smartlink.framework.mvpbase.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.lang.IllegalStateException

/**
 * Created by khanhnguyen on 13/09/2017
 */
open class MVPAppCompatActivity<P : MVPPresenter<V, S>, V : MVPPresenter.View, S : MVPPresenter.State> : AppCompatActivity() {
    private var mPresenterManager: PresenterManager<P>? = null
    private var mState: S? = null

    /**
     * Provides presenter.
     * @return IPresenter if it was created.
     * *         null otherwise.
     */
    protected val presenter: P?
        get() = mPresenterManager!!.getHostPresenter()

    open fun getPresenterManager() = mPresenterManager

    open fun onPresenterRestored(presenter: P) {

    }

    open fun onCreatePresenter(): P? = null

    open fun getPresenterView(): V? = null

    open fun retainPresenter(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        onRestoreState()
        super.onCreate(savedInstanceState)

        savedInstanceState?.apply {
            mState = getParcelable(PresenterManager.KEY_SAVED_STATE)
        }
    }

    override fun onStart() {
        super.onStart()
        attachViewToPresenter()
    }

    private fun attachViewToPresenter() {

        var presenter = presenter

        if (presenter == null) {
            presenter = onCreatePresenter()
        }

        val view = getPresenterView()

        if (presenter != null && view != null) {
            mPresenterManager!!.setHostPresenter(presenter)
            presenter.onAttachView(view, mState)
        } else if (presenter == null && view != null) {
            throw IllegalStateException("You created a view, but didn't provide a " + "presenter for it")
        } else if (presenter != null) {
            throw IllegalStateException("You provided a presenter, but didn't create view")
        }
    }

    private fun onRestoreState() {
        mPresenterManager = presenterManagerFromNCI

        if (mPresenterManager == null) {
            mPresenterManager = PresenterManager<P>()
        } else if (presenter != null) {
            onPresenterRestored(mPresenterManager?.getHostPresenter()!!)
        }
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        val nci = NonConfigurationInstances<P>()

        nci.presenterManager = mPresenterManager

        nci.custom = onRetainCustomNonConfigurationObject()
        return nci
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(PresenterManager.KEY_SAVED_STATE, presenter?.getState())
    }

    override fun onStop() {
        super.onStop()
        detachViewFromPresenter()
    }

    private fun detachViewFromPresenter() {
        val presenter = presenter
        presenter?.onDetachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!retainPresenter() || !isChangingConfigurations) {
            val presenter = presenter
            if (presenter != null) {
                mPresenterManager!!.setHostPresenter(null)
                presenter.onDestroy()
            }
        }
    }

    private val presenterManagerFromNCI: PresenterManager<P>?
        get() {
            val nci = lastCustomNonConfigurationInstance as NonConfigurationInstances<P>?
            if (nci != null) {
                return nci.presenterManager
            }
            return null
        }

    /**
     * Use this instead of [.onRetainCustomNonConfigurationInstance]}.
     * Retrieve later with [.getLastCustomNonConfigurationObject].
     */
    fun onRetainCustomNonConfigurationObject(): Any? {
        return null
    }

    /**
     * @return value previously returned from
     * *         [.onRetainCustomNonConfigurationObject]
     */
    val lastCustomNonConfigurationObject: Any?
        get() {
            val nci = lastCustomNonConfigurationInstance as NonConfigurationInstances<P>?
            return nci?.custom
        }

    internal class NonConfigurationInstances<P : MVPPresenter<*, *>> {
        var custom: Any? = null
        var presenterManager: PresenterManager<P>? = null
    }
}