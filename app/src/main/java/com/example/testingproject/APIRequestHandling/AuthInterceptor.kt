package com.example.testingproject.APIRequestHandling

import android.content.Context
import android.util.Log
import com.example.testingproject.sharedPreference.LoginStatusPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    //Shared Preference to save login informations (ID, Token, etc)
    private val loginPreference = LoginStatusPreference(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        if(loginPreference.getUserLoggedStatus().isLoggedIn == true){
            val currentToken = loginPreference.getLoginToken()

            Log.i("usedToken", "Token : $currentToken")

            currentToken?.let {
                requestBuilder.addHeader("Authorization", it)
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}