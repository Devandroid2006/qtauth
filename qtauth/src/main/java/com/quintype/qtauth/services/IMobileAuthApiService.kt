package com.quintype.qtauth.services

import com.quintype.qtauth.models.AuthMetadata
import com.quintype.qtauth.models.MemberRequestModel
import com.quintype.qtauth.models.MemberResponseModel
import com.quintype.qtauth.utils.*
import io.reactivex.Single
import retrofit2.http.*
import retrofit2.http.POST

interface IMobileAuthApiService {
    @POST("/api/member/login")
    fun memberLogin(
        @Body memberRequestModel: MemberRequestModel
    ): Single<MemberResponseModel>
//    @POST("/api/member/login")
//    fun memberLogin(
//        @Query(QUERY_PARAM_EMAIL) email: String?,
//        @Query(QUERY_PARAM_PASSWORD) password: String?
//    ): Single<MemberResponseModel>

    @POST("/api/member")
    fun memberRegistration(
        @Query(QUERY_PARAM_WELCOME_DELAY) welcomeDelay: Int,
        @Query(QUERY_PARAM_NAME) memberName: String?,
        @Query(QUERY_PARAM_EMAIL) memberEmail: String?,
        @Query(QUERY_PARAM_USERNAME) memberUserName: String?,
        @Query(QUERY_PARAM_PASSWORD) memberPassword: String?,
        @Query(QUERY_PARAM_DONT_LOGIN) memberDontLogin: Boolean?
    ): Single<MemberResponseModel>

    @GET("api/v1/members/me")
    fun memberDetail(
        @Header(QUERY_PARAM_X_QT_AUTH) xQTAuth: String?
    ): Single<MemberResponseModel>

    @GET("api/member/metadata")
    fun memberMetadata(
        @Header(QUERY_PARAM_X_QT_AUTH) xQTAuth: String?
    ): Single<MemberResponseModel>

    @GET("api/logout")
    fun memberLogout(
        @Header(QUERY_PARAM_X_QT_AUTH) xQTAuth: String?
    ): Single<AuthMetadata>
}