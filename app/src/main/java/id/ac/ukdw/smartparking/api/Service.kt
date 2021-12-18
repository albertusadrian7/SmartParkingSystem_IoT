package id.ac.ukdw.smartparking.api

import id.ac.ukdw.smartparking.model.kartu.GetCardResponse
import id.ac.ukdw.smartparking.model.login.OnLoginSuccessResponse
import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem
import id.ac.ukdw.smartparking.model.parkir.OnGetParkingSessionByUserIdResponse
import id.ac.ukdw.smartparking.model.pengunjung.CreatePengunjungResponse
import id.ac.ukdw.smartparking.model.voucher.CreateVoucherResponse
import retrofit2.Call
import retrofit2.http.*

interface Service {
    // Register Pengunjung
    @FormUrlEncoded
    @POST("userApi.php?function=insert_user")
    fun signUpPengunjung(
        @Field("id_user") id_user: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nama") nama: String,
        @Field("role") role: String
    ): Call<CreatePengunjungResponse>

    // Login Pengunjung
    @FormUrlEncoded
    @POST("userApi.php?function=login_user")
    fun loginPengunjung(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<OnLoginSuccessResponse>

    // Get Parking Session
    @FormUrlEncoded
    @POST("parkirApi.php?function=get_parking_session_by_user_id")
    fun getHistory(
        @Field("id_user") id_user: String
    ): Call<OnGetParkingSessionByUserIdResponse>

    // Get Kartu
    @FormUrlEncoded
    @POST("kartuApi.php?function=get_kartu_by_id")
    fun getKartu(
        @Field("id_user") id_user: String
    ): Call<GetCardResponse>

    // Top Up Saldo - > Buat voucher
    @FormUrlEncoded
    @POST("voucherApi.php?function=create_voucher")
    fun createVoucher(
        @Field("id_voucher") id_voucher: String,
        @Field("id_user") id_user: String,
        @Field("nominal") nominal: String
    ): Call<CreateVoucherResponse>

}