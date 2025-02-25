package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class InsertNewMemberRequestBody(

	@field:SerializedName("user_id")
	val userId: String? = null
)
