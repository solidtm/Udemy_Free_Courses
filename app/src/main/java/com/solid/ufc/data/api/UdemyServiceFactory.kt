package com.solid.ufc.data.api

import com.solid.ufc.BuildConfig
import com.solid.ufc.data.api.UdemyServiceFactory.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object UdemyServiceFactory {
    private const val BASE_URL: String = BuildConfig.BASE_URL

    fun createApiService(isDebug: Boolean, tokenInterceptor: TokenInterceptor): UdemyService {
        val okHttpClient: OkHttpClient = makeOkHttpClient(
            makeLoggingInterceptor((isDebug)), tokenInterceptor
        )
        return makeApiService(okHttpClient)
    }

    private fun makeApiService(okHttpClient: OkHttpClient): UdemyService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UdemyService::class.java)
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}