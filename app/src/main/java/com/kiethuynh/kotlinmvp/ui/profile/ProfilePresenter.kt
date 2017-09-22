package com.kiethuynh.kotlinmvp.ui.profile

import android.util.Log
import ch.smartlink.framework.mvpbase.BasePresenter
import com.kiethuynh.kotlinmvp.common.ErrorHandler
import com.kiethuynh.kotlinmvp.data.DataManager
import com.kiethuynh.kotlinmvp.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kiethuynh on 21/09/2017
 */
class ProfilePresenter @Inject constructor(private val mDataManager: DataManager,
                                           private val compositeDisposable: CompositeDisposable)
    : BasePresenter<ProfileContract.View, ProfileContract.State>(), ProfileContract.Presenter {

    override fun onAttachView(state: ProfileContract.State?) {
        if (mDataManager.getPreferencesHelper().getAccessToken().isNullOrBlank()) {
            view?.navigateLoginScreen()
            return@onAttachView
        }
        getProfile()
    }

    private fun getProfile() {
        view?.loadingIndicator(true)
        val observable = mDataManager.getDataSource().getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        var disposable = observable.subscribe(this::onGetProfileSuccess, this::onGetProfileFailed)
        disposable.dispose()

        disposable = observable.subscribe(this::onGetProfileSuccess, this::onGetProfileFailed)
    }

    private fun onGetProfileSuccess(user: User) {
        view?.loadingIndicator(false)
        view?.showUserInfo(user)
    }

    private fun onGetProfileFailed(throwable: Throwable) {
        view?.loadingIndicator(false)
        ErrorHandler.handleError(throwable, this)
    }

    override fun onDetachView() {

    }

    override fun onDestroy() {

    }
}