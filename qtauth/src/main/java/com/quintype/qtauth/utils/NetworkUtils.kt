package com.quintype.qtauth.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    val tag: String = NetworkUtils::class.java.simpleName

    fun isConnected(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.activeNetworkInfo.isConnectedOrConnecting
        } catch (e: Exception) {
            false
        }
    }
}