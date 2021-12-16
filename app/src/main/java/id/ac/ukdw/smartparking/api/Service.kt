package id.ac.ukdw.smartparking.api

import retrofit2.Call
import retrofit2.http.*

interface Service {
//
//    // Register Warga
//    @FormUrlEncoded
//    @POST("warga")
//    fun signUpWarga(
//        @Field("kode_keluarga") kode_keluarga: String,
//        @Field("nama") nama: String,
//        @Field("email") email: String,
//        @Field("password") password: String,
//        @Field("no_hp") no_hp: String,
//        @Field("gender") gender: String
//    ): Call<CreateWargaResponse>
//
//    // Login Warga
//    @FormUrlEncoded
//    @POST("warga/login")
//    fun loginWarga(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<OnLoginSuccessResponse>
//
//    // Register Pengurus
//    @FormUrlEncoded
//    @POST("pengurus")
//    fun signUpPengurus(
//        @Field("kode_rt") kode_rt: String,
//        @Field("nama") nama: String,
//        @Field("email") email: String,
//        @Field("password") password: String,
//        @Field("no_hp") no_hp: String,
//        @Field("gender") gender: String
//    ): Call<CreatePengurusResponse>
//
//    // Login Pengurus
//    @FormUrlEncoded
//    @POST("pengurus/login")
//    fun loginPengurus(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<OnLoginSuccessResponse>
//
//    // Get Produk Warga yang Login
//    @GET("produk/keluarga")
//    fun getProdukByLogin(
//        @Header("Authorization") authHeader: String
//    ): Call<GetAllProductFamilyResponse>
//
//    // Tambah Produk
//    @FormUrlEncoded
//    @POST("produk")
//    fun tambahProduk(
//        @Header("Authorization") authHeader: String,
//        @Field("nama") nama: String,
//        @Field("detail") detail: String,
//        @Field("gambar") gambar: String,
//        @Field("harga") harga: String,
//    ): Call<CreateProductResponse>
//
//    // Edit Produk
//    @FormUrlEncoded
//    @PUT("produk/{product_id}")
//    fun editProduk(
//        @Header("Authorization") authHeader: String,
//        @Path("product_id") product_id: String,
//        @Field("nama") nama: String,
//        @Field("detail") detail: String,
//        @Field("gambar") gambar: String,
//        @Field("harga") harga: String,
//    ): Call<UpdateProductResponse>
//
//
//    @DELETE("produk/{product_id}")
//    fun deleteProduk(
//        @Header("Authorization") authHeader: String,
//        @Path("product_id") product_id: String,
//    ): Call<DeleteProductResponse>



}