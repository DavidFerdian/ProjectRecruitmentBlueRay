package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class CustomerRegisterMandatoryRequestBody(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null
)
