package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class LoginValidationRequestBody(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null
)
