package com.solid.ufc.data.api

//import android.util.Base64
//import android.util.Base64.*
import android.annotation.SuppressLint
import android.util.Base64.NO_WRAP
import android.util.Base64.encodeToString
import com.solid.ufc.BuildConfig
import com.solid.ufc.util.SharePreference
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(private val sharePreference: SharePreference) : Interceptor {

    private var username: String? = null
    private var password: String? = null
    private var encodedValue : String? = null



    @SuppressLint("NewApi")
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()){
            password = sharePreference.username
            password = sharePreference.password

            encodedValue = "$username:$password"
            encodedValue = encodeToString(encodedValue!!.toByteArray(), NO_WRAP)
            encodedValue = Base64.getEncoder().encodeToString(encodedValue!!.toByteArray())
            encodedValue = BuildConfig.AUTH_TOKEN
        }

        var request = chain.request()
        if (request.header("Basic-Authentication") == null) {
            request = request.newBuilder()
                .addHeader("Authorization", "Basic $encodedValue")
                .build()
        }
        return chain.proceed(request)
    }
}