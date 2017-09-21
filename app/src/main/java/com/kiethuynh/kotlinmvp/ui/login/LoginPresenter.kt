package com.kiethuynh.kotlinmvp.ui.login

import android.util.Log
import ch.smartlink.framework.mvpbase.BasePresenter
import com.kiethuynh.kotlinmvp.common.ErrorHandler
import com.kiethuynh.kotlinmvp.data.DataManager
import com.kiethuynh.kotlinmvp.data.retrofit.response.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kiethuynh on 19/09/2017
 */
class LoginPresenter @Inject constructor(private val mDataManager: DataManager) : BasePresenter<LoginContract.View, LoginContract.State>(), LoginContract.Presenter {


    override fun onAttachView(state: LoginContract.State?) {

    }

    private fun login(user: User) {
        view?.loadingIndicator(true)
        mDataManager.getDataSource().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { header ->
                            view?.loadingIndicator(false)
                            mDataManager.getPreferencesHelper().setAccessToken(header)
                            view?.goToProfileScreen()
                        },
                        { throwable ->
                            view?.loadingIndicator(false)
                            ErrorHandler.handleError(throwable, this)
                        })
    }

    override fun onLoginButtonClick(username: String, password: String) {
        login(User(username, password))
    }

    override fun onDetachView() {
    }

    override fun onDestroy() {
    }


}