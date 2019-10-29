package com.quintype.qtauth.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemberResponseModel(
    @SerializedName("member")
    @Expose
    var member: Member? = null
) : Parcelable

@Parcelize
data class Member(
    @SerializedName("verification-status")
    @Expose
    var verificationStatus: String? = null,
    @SerializedName("updated-at")
    @Expose
    var updatedAt: Long? = null,
    @SerializedName("email")
    @Expose
    var email: String? = null,
    @SerializedName("slug")
    @Expose
    var slug: String? = null,
    @SerializedName("last-name")
    @Expose
    var lastName: String? = null,
    @SerializedName("publisher-id")
    @Expose
    var publisherId: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("avatar-url")
    @Expose
    var avatarUrl: String? = null,
    @SerializedName("source")
    @Expose
    var source: String? = null,
    @SerializedName("first-name")
    @Expose
    var firstName: String? = null,
    @SerializedName("communication-email")
    @Expose
    var communicationEmail: String? = null,
    @SerializedName("bio")
    @Expose
    var bio: String? = null,
    @SerializedName("can-login")
    @Expose
    var canLogin: Boolean? = null,
    @SerializedName("id")
    @Expose
    var id: Long? = null,
    @SerializedName("avatar-s3-key")
    @Expose
    var avatarS3Key: String? = null,
    @SerializedName("twitter-handle")
    @Expose
    var twitterHandle: String? = null,
    @SerializedName("created-at")
    @Expose
    var createdAt: Long? = null,
    @SerializedName("metadata")
    @Expose
    var metadata: AuthMetadata? = null,
    @SerializedName("error")
    @Expose
    var error: AuthError? = null
) : Parcelable

@Parcelize
data class AuthMetadata(
    @SerializedName("is-bridge-keeper-user-created")
    @Expose
    var isBridgeKeeperUserCreated: Boolean? = null
) : Parcelable


@Parcelize
data class AuthError(
    @SerializedName("message")
    @Expose
    var message: String? = null
) : Parcelable