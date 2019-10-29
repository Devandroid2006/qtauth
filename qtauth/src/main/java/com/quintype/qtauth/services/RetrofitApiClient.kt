package com.quintype.qtauth.services

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.quintype.qtauth.utils.AuthConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiClient {
    companion object {
        private var mRetrofit: Retrofit? = null

        var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

        var builder: OkHttpClient.Builder = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .sslSocketFactory(TLSSocketFactory())
            .hostnameVerifier { hostname, session -> true }
            .connectTimeout(30, TimeUnit.SECONDS)

        fun getRetrofitAPiClient(): Retrofit {
            if (AuthConstants.baseUrl != null)
                mRetrofit = Retrofit.Builder()
                    .baseUrl(AuthConstants.baseUrl!!)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return mRetrofit as Retrofit
        }
    }
}