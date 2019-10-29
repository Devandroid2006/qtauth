package com.quintype.qtauth.utils

import com.google.gson.Gson
import com.quintype.qtauth.models.AuthConfigModel

class AuthConstants {
    companion object {
        var baseUrl: String? = null

        fun initBaseUrl(mBaseUrl: String) {
            baseUrl = mBaseUrl
        }

        fun initAuthConfig(mAuthConfig: AuthConfigModel) {
//            val authConfigModel = Gson().fromJson<AuthConfigModel>(mAuthConfig.trim(), AuthConfigModel::class.java)
//            baseUrl = mAuthConfig.baseUrl
        }
    }
}

const val QUERY_PARAM_EMAIL = "email"
const val QUERY_PARAM_PASSWORD = "password"
const val QUERY_PARAM_WELCOME_DELAY = "send-welcome-email-delay"
const val QUERY_PARAM_NAME = "name"
const val QUERY_PARAM_USERNAME = "username"
const val QUERY_PARAM_DONT_LOGIN = "dont-login"
const val QUERY_PARAM_X_QT_AUTH = "X-QT-AUTH"
