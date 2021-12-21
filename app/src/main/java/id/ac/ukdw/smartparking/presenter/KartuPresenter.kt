package id.ac.ukdw.smartparking.presenter

import id.ac.ukdw.smartparking.model.kartu.GetCardResponse
import id.ac.ukdw.smartparking.model.kartu.GetKartuItem
import id.ac.ukdw.smartparking.view.viewInterface.KartuInterface
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.view.pengelola.PengelolaDashboardFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KartuPresenter(private val activity: Activity, private var view: KartuInterface, private var fragment: View) {
    fun getKartuPresenter(id_user: String) {
        RetrofitService
            .getService()
            .getKartu(id_user)
            .enqueue(object : Callback<GetCardResponse> {
                override fun onResponse(
                    call: Call<GetCardResponse>,
                    response: Response<GetCardResponse>
                ) {
                    when(response.isSuccessful){
                        true -> {
                            if (response.body()?.status == 1) {
                                Log.i("Hasil", "${response.body()?.data}")
                                val result = response.body()?.data as List<GetKartuItem>
                                view.resultCardSuccess(result, fragment)
//                                Toast.makeText(activity,"Pesan: ${response.message()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        false -> {
                            Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<GetCardResponse>, t: Throwable) {
                    view.resultCardFailed(t)
                }
            })
    }

}