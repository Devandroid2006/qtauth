package com.quintype.qtauth.utils

import android.os.Handler
import android.os.Looper

object DebounceHandler {

    private val mHandler = Handler(Looper.getMainLooper())

    fun handle(runnable: Runnable, delay: Long = 280L) {
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed(runnable, delay)
    }

    fun clearAll(){
        mHandler.removeCallbacksAndMessages(null)
    }
}