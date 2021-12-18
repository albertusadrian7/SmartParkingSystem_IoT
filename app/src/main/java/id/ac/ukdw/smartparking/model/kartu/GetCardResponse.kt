package id.ac.ukdw.smartparking.model.kartu

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetCardResponse(

	@field:SerializedName("data")
	val data: List<GetKartuItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class GetKartuItem(

	@field:SerializedName("card_uid")
	val cardUid: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("saldo")
	val saldo: String? = null
) : Parcelable
