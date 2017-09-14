package com.kiethuynh.kotlinmvp.ui

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by kiethuynh on 14/09/2017.
 */
class MainState(private var number: Int) : MainContract.State, Parcelable {
    override fun getNumber(): Int {
        return number
    }

    constructor(source: Parcel) : this(
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(number)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MainState> = object : Parcelable.Creator<MainState> {
            override fun createFromParcel(source: Parcel): MainState = MainState(source)
            override fun newArray(size: Int): Array<MainState?> = arrayOfNulls(size)
        }
    }
}