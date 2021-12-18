package id.ac.ukdw.smartparking.model.voucher

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetVoucherResponse(

	@field:SerializedName("data")
	val data: List<VoucherItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class VoucherItem(

	@field:SerializedName("nominal")
	val nominal: String? = null,

	@field:SerializedName("kode_voucher")
	val kodeVoucher: String? = null,

	@field:SerializedName("id_voucher")
	val idVoucher: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
