package com.quintype.qtauth.core

import com.quintype.qtauth.models.MemberRequestModel

interface LoginManager {

    fun loginWithFB(memberRequestModel: MemberRequestModel)

    fun loginWithGmail(memberRequestModel: MemberRequestModel)

    fun loginWithTwitter(memberRequestModel: MemberRequestModel)

    fun loginWithLinkedIn(memberRequestModel: MemberRequestModel)

    fun loginWithEmail(memberRequestModel: MemberRequestModel)

    fun memberLogin(memberRequestModel: MemberRequestModel)

    fun memberRegistration(memberRequestModel: MemberRequestModel)

    fun memberDetail(mXQTAuth: String?)

    fun memberMetadata(mXQTAuth: String?)

    fun memberLogout(mXQTAuth: String?)
}