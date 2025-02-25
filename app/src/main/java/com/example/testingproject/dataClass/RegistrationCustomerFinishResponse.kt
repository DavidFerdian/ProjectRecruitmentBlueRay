package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class RegistrationCustomerFinishResponse(

	@field:SerializedName("action")
	val action: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("customer")
	val customer: Customer? = null
)

data class Customer(

	@field:SerializedName("birthday")
	val birthday: String? = null,

	@field:SerializedName("customer_type")
	val customerType: String? = null,

	@field:SerializedName("google_id")
	val googleId: Any? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("paylater_used")
	val paylaterUsed: Int? = null,

	@field:SerializedName("facebook_link")
	val facebookLink: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("google_link")
	val googleLink: Any? = null,

	@field:SerializedName("membership_expiry_date")
	val membershipExpiryDate: String? = null,

	@field:SerializedName("phone_number_2")
	val phoneNumber2: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("province")
	val province: Int? = null,

	@field:SerializedName("register_code")
	val registerCode: String? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id_card_address")
	val idCardAddress: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("membership_type")
	val membershipType: String? = null,

	@field:SerializedName("membership_almost_expired")
	val membershipAlmostExpired: Boolean? = null,

	@field:SerializedName("id_card_name")
	val idCardName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("sub_district")
	val subDistrict: Int? = null,

	@field:SerializedName("status_paylater")
	val statusPaylater: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("birth_place")
	val birthPlace: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("apple_id")
	val appleId: Any? = null,

	@field:SerializedName("sales_tag")
	val salesTag: Any? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("facebook_id")
	val facebookId: Any? = null,

	@field:SerializedName("membership_saldo")
	val membershipSaldo: Int? = null,

	@field:SerializedName("id_card_image")
	val idCardImage: String? = null,

	@field:SerializedName("paylater_limit")
	val paylaterLimit: Int? = null,

	@field:SerializedName("nationality")
	val nationality: Any? = null,

	@field:SerializedName("district")
	val district: Int? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("id_card_number")
	val idCardNumber: String? = null,

	@field:SerializedName("customer_id")
	val customerId: Int? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Company(

	@field:SerializedName("company_type")
	val companyType: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("transaction_estimation")
	val transactionEstimation: String? = null,

	@field:SerializedName("company_id")
	val companyId: Int? = null,

	@field:SerializedName("ktp")
	val ktp: String? = null,

	@field:SerializedName("nib")
	val nib: String? = null,

	@field:SerializedName("npwp")
	val npwp: String? = null,

	@field:SerializedName("company_email")
	val companyEmail: String? = null,

	@field:SerializedName("company_address")
	val companyAddress: String? = null,

	@field:SerializedName("other_document")
	val otherDocument: String? = null,

	@field:SerializedName("company_fax_number")
	val companyFaxNumber: String? = null,

	@field:SerializedName("company_description")
	val companyDescription: String? = null,

	@field:SerializedName("passport")
	val passport: String? = null,

	@field:SerializedName("company_name")
	val companyName: String? = null,

	@field:SerializedName("company_phone_number")
	val companyPhoneNumber: String? = null,

	@field:SerializedName("company_photo")
	val companyPhoto: String? = null,

	@field:SerializedName("customer_estimation")
	val customerEstimation: String? = null,

	@field:SerializedName("skdu")
	val skdu: String? = null,

	@field:SerializedName("product_category")
	val productCategory: ProductCategory? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ProductCategory(

	@field:SerializedName("category_description")
	val categoryDescription: String? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null
)
