package com.example.testingproject.util

import com.example.testingproject.dataClass.DefaultResponse
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.Response

object BadClientResponseMessage {
    fun getMessage(response: Response<*>): String? {
        val adapter: TypeAdapter<DefaultResponse> =
            Gson().getAdapter(DefaultResponse::class.java)

        val responseBody = adapter.fromJson(response.errorBody()?.string())
        return responseBody.message?.substringAfter("- ")
    }
}