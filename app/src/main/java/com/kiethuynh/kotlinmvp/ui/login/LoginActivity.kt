package com.kiethuynh.kotlinmvp.ui.login

import android.content.Intent
import android.os.Bundle
import ch.smartlink.framework.mvpbase.BaseActivity
import com.kiethuynh.kotlinmvp.R
import com.kiethuynh.kotlinmvp.ui.profile.ProfileActivity
import com.kiethuynh.kotlinmvp.utils.hide
import com.kiethuynh.kotlinmvp.utils.show
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by kiethuynh on 19/09/2017
 */
class LoginActivity : BaseActivity<LoginContract.Presenter, LoginContract.View, LoginContract.State>(), LoginContract.View {
    @Inject lateinit var mainPresenter: Lazy<LoginContract.Presenter>

    override fun onCreatePresenter(): LoginContract.Presenter? = mainPresenter.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent?.inject(this)

        btnLogin.setOnClickListener {
            presenter?.onLoginButtonClick(edtUsername.text.toString(), edtPassword.text.toString())
        }
    }

    override fun goToProfileScreen() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun loadingIndicator(enable: Boolean) {
        if (enable)
            progressBar.show()
        else
            progressBar.hide()
    }
}