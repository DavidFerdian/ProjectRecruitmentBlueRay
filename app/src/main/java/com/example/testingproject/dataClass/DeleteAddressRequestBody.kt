package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class DeleteAddressRequestBody(

	@field:SerializedName("address_id")
	val addressId: Int? = null
)
