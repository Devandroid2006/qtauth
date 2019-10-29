package com.quintype.qtauth.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.quintype.qtauth.core.LiveDataWrapper
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<T>(application: Application) : AndroidViewModel(application) {
    private var liveData: MutableLiveData<LiveDataWrapper<T>>? = MutableLiveData()

    val mCompositeDisposable = CompositeDisposable()

    fun getLiveDataObservable() = liveData

    /**
     * this function is to notify the subscriber if the response throws an exception
     */
    fun notifyError(error: Throwable) {
        Log.e("Error notified : ", error.toString())
        liveData?.value = LiveDataWrapper(error = error)
    }

    /**
     * This function is to notify the dtaa
     */
    fun notifyData(data: T) {
        Log.d("data is == ", data.toString())
        liveData?.value = LiveDataWrapper(data = data)
    }
}