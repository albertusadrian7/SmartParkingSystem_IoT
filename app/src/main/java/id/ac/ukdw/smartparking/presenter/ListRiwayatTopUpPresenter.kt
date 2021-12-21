package id.ac.ukdw.smartparking.presenter

import android.app.Activity
import android.util.Log
import android.widget.Toast
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.model.voucher.GetVoucherResponse
import id.ac.ukdw.smartparking.model.voucher.VoucherItem
import id.ac.ukdw.smartparking.view.viewInterface.RiwayatTopUpInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListRiwayatTopUpPresenter(private val activity: Activity, private var view: RiwayatTopUpInterface) {
    fun getHistoryTopUpPresenter(id_user: String) {
        RetrofitService
            .getService()
            .getTopUpHistory(id_user)
            .enqueue(object : Callback<GetVoucherResponse> {
                override fun onResponse(
                    call: Call<GetVoucherResponse>,
                    response: Response<GetVoucherResponse>
                ) {
                    when(response.isSuccessful){
                        true -> {
                            if (response.body()?.status == 1) {
                                Log.i("Hasil", "${response.body()?.data}")
                                val result = response.body()?.data as List<VoucherItem>
                                view.resultSuccess(result)
//                                Toast.makeText(activity,"Pesan: ${response.message()}", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        false -> {
                            Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<GetVoucherResponse>, t: Throwable) {
                    view.resultFailed(t)
                }
            })
    }
}