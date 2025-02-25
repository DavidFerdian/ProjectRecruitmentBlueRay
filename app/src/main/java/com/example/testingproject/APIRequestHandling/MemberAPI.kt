package com.example.testingproject.APIRequestHandling

import com.example.testingproject.dataClass.LoginSuccesResponse
import com.example.testingproject.dataClass.LoginValidationRequestBody
import retrofit2.Call
import retrofit2.http.*

interface MemberAPI {
    /*##########################################################################################################*/
    /*
    * Member-Related Service (Registration, Login, Show Profile, Update Profile, and Change Login Password)
    * */


    @POST("/api/blueray/customer/login")
    fun loginValidation(
        @Body postedInformation: LoginValidationRequestBody
    ): Call<LoginSuccesResponse>

   }