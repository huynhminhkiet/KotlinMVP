package com.kiethuynh.kotlinmvp.ui.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ch.smartlink.framework.mvpbase.BaseActivity
import com.kiethuynh.kotlinmvp.R

class ProfileActivity : AppCompatActivity()/*BaseActivity<ProfileContract.Presenter, ProfileContract.View, ProfileContract.State>(), ProfileContract.View*/ {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}
