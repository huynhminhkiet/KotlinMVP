package com.kiethuynh.kotlinmvp.ui.main

import android.util.Log
import ch.smartlink.framework.mvpbase.BasePresenter
import com.kiethuynh.kotlinmvp.data.DataManager
import com.kiethuynh.kotlinmvp.data.retrofit.response.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kiethuynh on 19/09/2017
 */
class MainPresenter @Inject constructor(val mDataManager: DataManager) : BasePresenter<MainContract.View, MainContract.State>(), MainContract.Presenter {
    override fun onAttachView(state: MainContract.State?) {
        login(User("kiethuynh", "123456"))
    }

    private fun login(user: User) {
        view?.loadingIndicator(true)
        mDataManager.getDataSource().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { header ->
                            view?.loadingIndicator(false)
                            Log.d("Token", header)
                        },
                        { throwable ->
                            view?.loadingIndicator(false)
                            throwable.printStackTrace()
                        })
    }

    override fun onDetachView() {
    }

    override fun onDestroy() {
    }


}