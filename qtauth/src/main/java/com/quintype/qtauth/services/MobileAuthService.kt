package com.quintype.qtauth.services

import com.google.gson.Gson
import com.quintype.qtauth.models.MemberRequestModel

class MobileAuthService {
    companion object {
        private var iMobileAuthApiService: IMobileAuthApiService? = null
        private var mMobileAuthService: MobileAuthService? = null

        @Synchronized
        fun getInstance(): MobileAuthService {
            if (mMobileAuthService == null) {
                mMobileAuthService = MobileAuthService()

                iMobileAuthApiService = RetrofitApiClient.getRetrofitAPiClient().create(IMobileAuthApiService::class.java)
            }
            return mMobileAuthService as MobileAuthService
        }
    }

    fun memberLogin(memberRequestModel: MemberRequestModel) = iMobileAuthApiService?.memberLogin(memberRequestModel)

    fun memberRegistration(name: String?, email: String?, username: String?, password: String?) =
        iMobileAuthApiService?.memberRegistration(0, name, email, username, password, false)

    fun memberMetadata(mXQTAuth: String) = iMobileAuthApiService?.memberMetadata(mXQTAuth)

    fun memberDetail(mXQTAuth: String) = iMobileAuthApiService?.memberDetail(mXQTAuth)

    fun memberLogout(mXQTAuth: String) = iMobileAuthApiService?.memberLogout(mXQTAuth)
}