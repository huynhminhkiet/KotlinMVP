package ch.smartlink.framework.mvpbase.core

import android.os.Parcelable
import android.support.annotation.Nullable

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface MVPPresenter<in V : MVPPresenter.View, S : MVPPresenter.State> {

    fun onAttachView(view: V, state: S?)

    fun onDetachView()

    fun getState() : S?

    fun onDestroy()

    interface View

    interface State : Parcelable

}