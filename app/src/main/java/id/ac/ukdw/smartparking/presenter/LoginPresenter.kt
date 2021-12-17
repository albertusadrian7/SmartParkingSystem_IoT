package id.ac.ukdw.smartparking.presenter

import android.app.Activity
import android.util.Log
import android.widget.Toast
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.model.login.OnLoginSuccessResponse
import id.ac.ukdw.smartparking.view.viewInterface.LoginInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "LOGIN PRESENTER"
class LoginPresenter(private val activity: Activity, private val view: LoginInterface) {
    //Login pengunjung
    fun loginPengunjung(
        username: String,
        password: String
    ) {
        pushToCloud(username, password)
    }

    //push new data to cloud so user can register to server
    private fun pushToCloud(
        username: String,
        password: String
    ) {
        RetrofitService
            .getService()
            .loginPengunjung(
                username,
                password
            )
            .enqueue(object : Callback<OnLoginSuccessResponse> {
                override fun onResponse(
                    call: Call<OnLoginSuccessResponse>,
                    response: Response<OnLoginSuccessResponse>
                ) {
                    when(response.isSuccessful){
                        true -> {
                            if (response.body()?.status == 1){
                                saveSession(
                                    username,
                                    password
                                )
                                view.onLoginSuccess(activity.getString(R.string.login_sukses))
                                Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        false -> {
                            view.onLoginFail(activity.getString(R.string.login_gagal))
                            Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<OnLoginSuccessResponse>, t: Throwable) {
                    view.onLoginFail(activity.getString(R.string.login_gagal))
                    Log.i(TAG, "onFailure: ${t.message}")
                }
            })
    }

    //save user data to session
    private fun saveSession(
        username: String,
        password: String
    ) {
        val adminSession = UserSession(activity)
        adminSession.save(UserSession.SHARED_PREFERENCE_USERNAME_KEY,username)
        adminSession.save(UserSession.SHARED_PREFERENCE_PASSWORD_KEY,password)
//        view.onLoginSuccess(activity.getString(R.string.login_sukses))
    }

}