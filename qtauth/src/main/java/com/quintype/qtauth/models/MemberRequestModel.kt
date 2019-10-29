package com.quintype.qtauth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Parcelize
data class MemberRequestModel(

    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("email")
    @Expose
    var email: String? = null,
    @SerializedName("username")
    @Expose
    var username: String? = null,
    @SerializedName("metadata")
    @Expose
    var metadata: MemberAuthMetadata? = null,
    @SerializedName("password")
    @Expose
    var password: String? = null,
    @SerializedName("send-welcome-email-delay")
    @Expose
    var sendWelcomeEmailDelay: Int? = null,
    @SerializedName("dont-login")
    @Expose
    var dontLogin: Boolean? = null
) : Parcelable

@Parcelize
data class MemberAuthMetadata(
    @SerializedName("phone-number")
    @Expose
    var phoneNumber: String? = null
) : Parcelable