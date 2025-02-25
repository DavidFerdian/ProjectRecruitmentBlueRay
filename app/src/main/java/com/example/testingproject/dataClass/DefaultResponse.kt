package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class DefaultResponse(

	@field:SerializedName("action")
	val action: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
