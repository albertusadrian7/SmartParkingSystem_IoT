package id.ac.ukdw.smartparking.presenter

import android.app.Activity
import android.util.Log
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.model.voucher.CreateVoucherResponse
import id.ac.ukdw.smartparking.view.viewInterface.TopUpInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "TOP UP"

class TopUpPresenter(private val activity: Activity, private val view: TopUpInterface) {
    // Buat voucher top up
    fun topUpPresenter(
        id_user: String,
        nominal: String
    ) {
        RetrofitService
            .getService()
            .createVoucher(
                "",
                id_user,
                nominal
            )
            .enqueue(object : Callback<CreateVoucherResponse> {
                override fun onResponse(
                    call: Call<CreateVoucherResponse>,
                    response: Response<CreateVoucherResponse>
                ) {
                    when(response.isSuccessful){
                        true -> {
                            if (response.body()?.status == 1){
                                view.onTopUpSuccess("Berhasil membuat voucher top up!")
                            }
                        }
                        false -> {
                            view.onTopUpFail("Gagal membuat voucher top up!")
                        }
                    }
                }
                override fun onFailure(call: Call<CreateVoucherResponse>, t: Throwable) {
                    view.onTopUpFail("Gagal membuat voucher top up!")
                    Log.i(TAG, "onFailure: ${t.message}")
                }
            })
    }
}