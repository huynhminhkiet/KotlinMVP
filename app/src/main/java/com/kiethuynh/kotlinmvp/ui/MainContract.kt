package com.kiethuynh.kotlinmvp.ui

import android.os.Parcelable

/**
 * Created by kiethuynh on 14/09/2017.
 */
interface MainContract {
    interface Presenter {
        fun bind(view: View?, state: State?)
        fun onStartButtonClick()
        fun getState(): State?
    }

    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun updateNumber(number: Int)
    }

    interface State: Parcelable {
        fun getNumber(): Int
    }
}