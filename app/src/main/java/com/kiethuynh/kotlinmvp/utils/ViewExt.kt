package com.kiethuynh.kotlinmvp.utils

import android.view.View

/**
 * Created by kiethuynh on 14/09/2017.
 */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}