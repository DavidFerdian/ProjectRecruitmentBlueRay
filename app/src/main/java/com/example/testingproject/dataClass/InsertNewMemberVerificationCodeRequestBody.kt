package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class InsertNewMemberVerificationCodeRequestBody(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null
)
