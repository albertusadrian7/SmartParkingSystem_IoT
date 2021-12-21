package id.ac.ukdw.smartparking.model.kartu

import com.google.gson.annotations.SerializedName

data class RegisterUserCardResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
