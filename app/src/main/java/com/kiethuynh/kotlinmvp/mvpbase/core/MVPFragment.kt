package ch.smartlink.framework.mvpbase.core

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import java.lang.IllegalStateException
import java.util.*

/**
 * Created by khanhnguyen on 13/09/2017
 */
@Suppress("UNCHECKED_CAST")
open class MVPFragment<P : MVPPresenter<V, S>, V : MVPPresenter.View, S : MVPPresenter.State> : Fragment() {
    private var mPresenterUUID: UUID? = null

    private var mPresenterManager: PresenterManager<*>? = null
    private var mState: S? = null

    /**
     * Provides presenter.

     * @return IPresenter if it was created.
     * * null otherwise.
     */
    protected val presenter: P?
        get() {
            if (mPresenterUUID != null) {
                return mPresenterManager!!.getPresenter(mPresenterUUID!!) as P
            }
            return null
        }

    /**
     * Called after presenter restored. Called before [.onCreate].
     * View is not attached to presenter at this moment.
     */
    protected fun onPresenterRestored(presenter: P) {

    }

    /**
     * Method to instantiate presenter. Called during [.onStart]

     * @return new IPresenter.
     * * null if [.getPresenterView] returns null.
     * * Otherwise [IllegalStateException] will be thrown.
     */
    protected fun onCreatePresenter(): P? {
        return null
    }

    /**
     * Method to instantiate IPresenter.View for presenter. Called during [.onStart]

     * @return View for presenter.
     * * null if [.onCreatePresenter] returns null.
     * * Otherwise [IllegalStateException] will be thrown.
     */
    protected open val presenterView: V?
        get() = null

    /**
     * Indicates if presenter should be kept or not.

     * @return true if presenter should be retained, false otherwise.
     * * Default value is true
     */
    protected fun retainPresenter(): Boolean {
        return true
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MVPAppCompatActivity<*, *, *>) {
            mPresenterManager = context.getPresenterManager()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        onRestoreState(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            mState = savedInstanceState.getParcelable<S>(PresenterManager.KEY_SAVED_STATE)
        }
    }

    private fun onRestoreState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mPresenterUUID = savedInstanceState.getSerializable(PRESENTER_SAVE_UUID) as UUID
            val presenter = presenter
            if (presenter != null) {
                onPresenterRestored(presenter)
            }
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

        val view = presenterView

        if (presenter != null && view != null) {
            if (mPresenterManager != null)
                mPresenterUUID = mPresenterManager!!.addPresenter(presenter)
            presenter.onAttachView(view, mState!!)
        } else if (presenter == null && view != null) {
            throw IllegalStateException("You provided a view, but didn't create presenter")
        } else if (presenter != null) {
            throw IllegalStateException("You created a presenter, but didn't provide a " + "view for it")
        }
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
        if (!retainPresenter() || !activity.isChangingConfigurations) {
            val presenter = presenter
            if (presenter != null) {
                if (mPresenterManager != null)
                    mPresenterManager!!.removePresenter(mPresenterUUID!!)
                presenter.onDestroy()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putSerializable(PRESENTER_SAVE_UUID, mPresenterUUID)
        outState.putParcelable(PresenterManager.KEY_SAVED_STATE, presenter!!.getState())
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        mPresenterManager = null
    }

    companion object {
        private val PRESENTER_SAVE_UUID = "IPresenter save uuid tag"
    }
}