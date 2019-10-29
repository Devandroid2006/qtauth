package com.quintype.qtauth.core

import android.app.Application

open class LoginMainApplication : Application() {

    companion object {
        var mInstance: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}