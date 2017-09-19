package ch.smartlink.framework.mvpbase.core

import java.util.*
import kotlin.collections.HashMap

/**
 * Created by khanhnguyen on 13/09/2017
 */
class PresenterManager<HP : MVPPresenter<*, *>> {

    private var mHostPresenter: HP? = null
    private val mPresenters = HashMap<UUID, MVPPresenter<*, *>>()

    fun getHostPresenter() = mHostPresenter

    fun setHostPresenter(presenter: HP?) {
        mHostPresenter = presenter
    }

    fun getPresenter(uuid: UUID): MVPPresenter<*, *> {
        return mPresenters[uuid]!!
    }

    fun addPresenter(presenter: MVPPresenter<*, *>): UUID {
        val uuid = UUID.randomUUID()
        mPresenters.put(uuid, presenter)
        return uuid
    }

    fun removePresenter(uuid: UUID): MVPPresenter<*, *> {
        return mPresenters.remove(uuid)!!
    }

    companion object {
        val KEY_SAVED_STATE = "saved_state"
    }
}