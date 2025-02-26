package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class DefaultAddressResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("action")
	val action: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Result(

	@field:SerializedName("is_primary")
	val isPrimary: Boolean? = null,

	@field:SerializedName("city_code")
	val cityCode: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Any? = null,

	@field:SerializedName("sub_district_name")
	val subDistrictName: String? = null,

	@field:SerializedName("district_name")
	val districtName: String? = null,

	@field:SerializedName("phone_number_2")
	val phoneNumber2: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("province")
	val province: Int? = null,

	@field:SerializedName("npwp_file")
	val npwpFile: String? = null,

	@field:SerializedName("email")
	val email: Any? = null,

	@field:SerializedName("lat")
	val lat: Any? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("sub_district")
	val subDistrict: Int? = null,

	@field:SerializedName("address_id")
	val addressId: Int? = null,

	@field:SerializedName("npwp")
	val npwp: Any? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("province_name")
	val provinceName: String? = null,

	@field:SerializedName("province_id")
	val provinceId: Int? = null,

	@field:SerializedName("sub_district_id")
	val subDistrictId: Int? = null,

	@field:SerializedName("district")
	val district: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("district_id")
	val districtId: Int? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null,

	@field:SerializedName("address_map")
	val addressMap: String? = null,

	@field:SerializedName("customer")
	val customer: Int? = null
)
