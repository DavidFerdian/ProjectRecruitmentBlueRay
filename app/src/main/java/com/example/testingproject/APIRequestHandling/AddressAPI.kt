package com.example.testingproject.APIRequestHandling

import com.example.testingproject.dataClass.AddressResponse
import com.example.testingproject.dataClass.DefaultAddressResponse
import com.example.testingproject.dataClass.DefaultResponse
import com.example.testingproject.dataClass.DeleteAddressRequestBody
import com.example.testingproject.dataClass.InsertNewMemberVerificationCodeRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST


interface AddressAPI {
    /*##########################################################################################################*/
    /*
    * Address-Related Service
    * */

    @GET("/api/blueray/customer/address")
    fun getAddressList(): Call<AddressResponse>

    @DELETE("/api/blueray/customer/address/delete")
    fun deleteAddress( @Body postedInformation: DeleteAddressRequestBody): Call<DefaultResponse>

    @GET("/api/blueray/customer/address/primary")
    fun getDefaultAddress(): Call<DefaultAddressResponse>

    @POST("/api/blueray/customer/address/primary")
    fun selectAddress( @Body postedInformation: DeleteAddressRequestBody): Call<DefaultResponse>


}