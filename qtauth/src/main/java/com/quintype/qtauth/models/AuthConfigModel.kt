package com.quintype.qtauth.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthConfigModel(
//    @SerializedName("base_url")
//    @Expose
//    var baseUrl: String? = null,
    @SerializedName("publisher")
    @Expose
    var publisher: String? = null,
    @SerializedName("authProviders")
    @Expose
    var authProviders: List<String>? = null,
    @SerializedName("features")
    @Expose
    var features: List<String>? = null
) : Parcelable