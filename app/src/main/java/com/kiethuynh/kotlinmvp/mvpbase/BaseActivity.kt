package ch.smartlink.framework.mvpbase

import android.annotation.SuppressLint
import android.os.Bundle
import ch.smartlink.framework.mvpbase.core.MVPAppCompatActivity
import ch.smartlink.framework.mvpbase.core.MVPPresenter
import com.kiethuynh.kotlinmvp.MyApplication
import com.kiethuynh.kotlinmvp.di.component.ActivityComponent
import com.kiethuynh.kotlinmvp.di.component.DaggerActivityComponent
import com.kiethuynh.kotlinmvp.di.module.ActivityModule

/**
 * Created by khanhnguyen on 13/09/2017
 */
@Suppress("UNCHECKED_CAST")
@SuppressLint("Registered")
open class BaseActivity<P : MVPPresenter<V, S>, V : BaseView, S : MVPPresenter.State> : MVPAppCompatActivity<P, V, S>(), BaseView {
    protected var activityComponent: ActivityComponent? = null
        get() = field

    override fun hideKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLoadingIndicator(visible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as MyApplication).getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun getPresenterView(): V? {
        return this as V
    }
}