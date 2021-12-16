package id.ac.ukdw.smartparking.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val baseUrl = "https://gits-capstone.herokuapp.com/"

    private fun getInterseptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getInterseptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): Service = getRetrofit().create(Service::class.java)
}