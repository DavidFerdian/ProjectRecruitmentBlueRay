package com.example.testingproject.APIRequestHandling

import com.example.testingproject.dataClass.CustomerRegisterMandatoryRequestBody
import com.example.testingproject.dataClass.InsertNewMemberRequestBody
import com.example.testingproject.dataClass.InsertNewMemberVerificationCodeRequestBody
import com.example.testingproject.dataClass.RegisterMemberResponse
import com.example.testingproject.dataClass.RegistrationCustomerFinishResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationAPI {
    //Register a new member
    @POST("/api/blueray/customer/register/mini")
    fun insertNewMember(
        @Body postedInformation: InsertNewMemberRequestBody
    ): Call<RegisterMemberResponse>

    @POST("/api/blueray/customer/register/verify-code")
    fun customerRegisterVerifyCode(
        @Body postedInformation: InsertNewMemberVerificationCodeRequestBody
    ): Call<RegisterMemberResponse>


    @POST("/api/blueray/customer/register/resend-code")
    fun resendCode(
        @Body postedInformation: InsertNewMemberRequestBody
    ): Call<RegisterMemberResponse>

    @POST("/api/blueray/customer/register/mandatory")
    fun customerRegisterMandatory(
        @Body postedInformation: CustomerRegisterMandatoryRequestBody
    ): Call<RegistrationCustomerFinishResponse>


}
