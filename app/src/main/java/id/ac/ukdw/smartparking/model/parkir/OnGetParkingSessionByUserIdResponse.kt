package id.ac.ukdw.smartparking.model.parkir

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnGetParkingSessionByUserIdResponse(
	val data: List<GetParkingSessionItem?>? = null,
	val message: String? = null,
	val status: Int? = null
) : Parcelable

@Parcelize
data class GetParkingSessionItem(
	val waktu_masuk: String? = null,
	val total: String? = null,
	val waktu_keluar: String? = null,
	val id_parkir: String? = null,
	val card_uid: String? = null,
	val id_user: String? = null
) : Parcelable
