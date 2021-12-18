package id.ac.ukdw.smartparking.presenter

import android.app.Activity
import android.util.Log
import android.widget.Toast
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem
import id.ac.ukdw.smartparking.model.parkir.OnGetParkingSessionByUserIdResponse
import id.ac.ukdw.smartparking.view.viewInterface.RiwayatInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListRiwayatPresenter(private val activity: Activity, private var view: RiwayatInterface) {
    fun getHistoryPresenter(id_user: String) {
        RetrofitService
            .getService()
            .getHistory(id_user)
            .enqueue(object : Callback<OnGetParkingSessionByUserIdResponse> {
                override fun onResponse(
                    call: Call<OnGetParkingSessionByUserIdResponse>,
                    response: Response<OnGetParkingSessionByUserIdResponse>
                ) {
                    when(response.isSuccessful){
                        true -> {
                            if (response.body()?.status == 1){
                                Log.i("Hasil","${response.body()?.data}")
                                val result = response.body()?.data as List<GetParkingSessionItem>

                                view.resultSuccess(result)
                                Toast.makeText(activity,"Pesan: ${response.message()}", Toast.LENGTH_SHORT).show()
                                Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        false -> {
                            Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<OnGetParkingSessionByUserIdResponse>, t: Throwable) {
                    view.resultFailed(t)
                }
            })
    }

}