package com.kiethuynh.kotlinmvp.ui.profile

import android.os.Bundle
import ch.smartlink.framework.mvpbase.BaseActivity
import com.kiethuynh.kotlinmvp.R
import com.kiethuynh.kotlinmvp.data.model.User
import com.kiethuynh.kotlinmvp.utils.hide
import com.kiethuynh.kotlinmvp.utils.show
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_profile.*
import javax.inject.Inject

class ProfileActivity : BaseActivity<ProfileContract.Presenter, ProfileContract.View, ProfileContract.State>(), ProfileContract.View {
    @Inject lateinit var profilePresenter: Lazy<ProfileContract.Presenter>

    override fun onCreatePresenter(): ProfileContract.Presenter? = profilePresenter.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        activityComponent?.inject(this)
    }

    override fun showUserInfo(user: User) {
        tvFullName.text = user.fullname
    }

    override fun loadingIndicator(enable: Boolean) {
        if (enable)
            progressBar.show()
        else
            progressBar.hide()
    }
}
