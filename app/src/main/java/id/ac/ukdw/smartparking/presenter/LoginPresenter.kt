package id.ac.ukdw.smartparking.presenter

import android.app.Activity
import android.util.Log
import android.widget.Toast
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_ID_KEY
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_PASSWORD_KEY
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_ROLE_KEY
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_USERNAME_KEY
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
                                var data = response.body()?.data!!.get(0)!!
                                var role = data.role!!
                                var idUser = data.idUser!!
                                saveSession(
                                    idUser,
                                    username,
                                    password,
                                    role
                                )
                                view.onLoginSuccess(role)
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
        idUser: String,
        username: String,
        password: String,
        role: String
    ) {
        val userSession = UserSession(activity)
        userSession.save(SHARED_PREFERENCE_USERNAME_KEY,username)
        userSession.save(SHARED_PREFERENCE_PASSWORD_KEY,password)
        userSession.save(SHARED_PREFERENCE_ROLE_KEY,role)
        userSession.save(SHARED_PREFERENCE_ID_KEY,idUser)
//        view.onLoginSuccess(activity.getString(R.string.login_sukses))
    }

}