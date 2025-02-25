package com.example.testingproject.dataClass

import com.google.gson.annotations.SerializedName

data class LoginSuccesResponse(

	@field:SerializedName("company")
	val company: Any? = null,

	@field:SerializedName("freshchat_restore_id")
	val freshchatRestoreId: Any? = null,

	@field:SerializedName("login")
	val login: Boolean? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("customer")
	val customer: CustomerLogin? = null
)

data class TotalTransaction(

	@field:SerializedName("process")
	val process: Int? = null,

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("cancelled")
	val cancelled: Int? = null
)

data class UnpackingPolicy(

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class Settings(

	@field:SerializedName("unpacking_policy")
	val unpackingPolicy: UnpackingPolicy? = null
)

data class CustomerLogin(

	@field:SerializedName("google_id")
	val googleId: Any? = null,

	@field:SerializedName("mou_business")
	val mouBusiness: Any? = null,

	@field:SerializedName("phone_number_2")
	val phoneNumber2: Any? = null,

	@field:SerializedName("province")
	val province: Any? = null,

	@field:SerializedName("register_code")
	val registerCode: String? = null,

	@field:SerializedName("notif_sms")
	val notifSms: Boolean? = null,

	@field:SerializedName("settings")
	val settings: Settings? = null,

	@field:SerializedName("notif_whatsapp")
	val notifWhatsapp: Boolean? = null,

	@field:SerializedName("sub_district")
	val subDistrict: Any? = null,

	@field:SerializedName("assignment_status")
	val assignmentStatus: Any? = null,

	@field:SerializedName("assignment_user")
	val assignmentUser: Any? = null,

	@field:SerializedName("register_origin")
	val registerOrigin: String? = null,

	@field:SerializedName("customer_callback_url")
	val customerCallbackUrl: Any? = null,

	@field:SerializedName("birth_place")
	val birthPlace: Any? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("facebook_id")
	val facebookId: Any? = null,

	@field:SerializedName("id_card_image")
	val idCardImage: String? = null,

	@field:SerializedName("paylater_limit")
	val paylaterLimit: Int? = null,

	@field:SerializedName("nationality")
	val nationality: Any? = null,

	@field:SerializedName("additional_info")
	val additionalInfo: Any? = null,

	@field:SerializedName("district")
	val district: Any? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("freshchat_restore_id")
	val freshchatRestoreId: Any? = null,

	@field:SerializedName("id_card_number")
	val idCardNumber: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("birthday")
	val birthday: Any? = null,

	@field:SerializedName("customer_type")
	val customerType: String? = null,

	@field:SerializedName("gender")
	val gender: Any? = null,

	@field:SerializedName("paylater_used")
	val paylaterUsed: Int? = null,

	@field:SerializedName("facebook_link")
	val facebookLink: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("google_link")
	val googleLink: Any? = null,

	@field:SerializedName("customer_key")
	val customerKey: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("notif_mobile")
	val notifMobile: Boolean? = null,

	@field:SerializedName("notif_email")
	val notifEmail: Boolean? = null,

	@field:SerializedName("company")
	val company: Any? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("fcm_id")
	val fcmId: Any? = null,

	@field:SerializedName("total_transaction")
	val totalTransaction: TotalTransaction? = null,

	@field:SerializedName("address")
	val address: Any? = null,

	@field:SerializedName("wallet")
	val wallet: Int? = null,

	@field:SerializedName("status_paylater")
	val statusPaylater: Any? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("apple_id")
	val appleId: Any? = null,

	@field:SerializedName("freshchat_titipkirimin_restore_id")
	val freshchatTitipkiriminRestoreId: Any? = null,

	@field:SerializedName("titipkirimin_group")
	val titipkiriminGroup: Any? = null,

	@field:SerializedName("is_titipkirimin_complete")
	val isTitipkiriminComplete: Boolean? = null,

	@field:SerializedName("referral_code")
	val referralCode: Any? = null,

	@field:SerializedName("customer_id")
	val customerId: Int? = null,

	@field:SerializedName("postal_code")
	val postalCode: Any? = null,

	@field:SerializedName("user")
	val user: Any? = null,

	@field:SerializedName("username")
	val username: Any? = null,

	@field:SerializedName("customer_referral")
	val customerReferral: Any? = null
)
