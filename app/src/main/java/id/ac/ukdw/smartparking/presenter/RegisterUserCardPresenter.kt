package id.ac.ukdw.smartparking.presenter

import android.app.Activity
import android.util.Log
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.model.kartu.RegisterUserCardResponse
import id.ac.ukdw.smartparking.view.viewInterface.RegisterUserCardInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "REGISTER CARD"

class RegisterUserCardPresenter(private val activity: Activity, private val view: RegisterUserCardInterface) {
    // Buat voucher top up
    fun registerUserCardPresenter(
        card_uid: String,
        id_user: String
    ) {
        RetrofitService
            .getService()
            .registerUserCard(
                card_uid,
                id_user
            )
            .enqueue(object : Callback<RegisterUserCardResponse> {
                override fun onResponse(
                    call: Call<RegisterUserCardResponse>,
                    response: Response<RegisterUserCardResponse>
                ) {
                    when(response.isSuccessful){
                        true -> {
                            if (response.body()?.status == 1){
                                view.onRegisterUserCardSuccess(response.body()?.message!!)
                            } else {
                                view.onRegisterUserCardFail(response.body()?.message!!)
                            }
                        }
                        false -> {
                            view.onRegisterUserCardFail(response.body()?.message!!)
                        }
                    }
                }
                override fun onFailure(call: Call<RegisterUserCardResponse>, t: Throwable) {
                    view.onRegisterUserCardFail("Gagal mendaftarkan kartu!")
                    Log.i(TAG, "onFailure: ${t.message}")
                }
            })
    }
}