package com.kiethuynh.kotlinmvp.utils

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by kiethuynh on 21/09/2017
 */
fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this,  message, Toast.LENGTH_SHORT).show()
}