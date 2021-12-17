package id.ac.ukdw.smartparking.presenter

import android.app.Activity
import android.util.Log
import android.widget.Toast
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.api.RetrofitService
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.extentions.UserValidator
import id.ac.ukdw.smartparking.model.pengunjung.CreatePengunjungResponse
import id.ac.ukdw.smartparking.view.viewInterface.RegisterInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "REGISTER PRESENTER"
class RegisterPresenter(private val activity: Activity, private val view: RegisterInterface) {
    //register new user
    fun registerPengunjung(
        id_user: String,
        username: String,
        email: String,
        password: String,
        name: String,
        role: String) {
        when (UserValidator.verifyData(email, password, name)) {
            true -> {
                pushToCloud(id_user,username, email, password, name, role)
            }
        }
    }

    //push new data to cloud so user can register to server
    private fun pushToCloud(
        id_user: String,
        username: String,
        email: String,
        password: String,
        name: String,
        role: String
    ) {
        RetrofitService
            .getService()
            .signUpPengunjung(
                id_user,
                username,
                email,
                password,
                name,
                role)
            .enqueue(object : Callback<CreatePengunjungResponse> {
                override fun onResponse(
                    call: Call<CreatePengunjungResponse>,
                    response: Response<CreatePengunjungResponse>
                ) {
                    saveSession(
                        id_user,
                        username,
                        email,
                        password,
                        name,
                        role
                    )
                    Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<CreatePengunjungResponse>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }
            })
    }

    //save user data to session
    private fun saveSession(
        id_user: String,
        username: String,
        name: String,
        email: String,
        password: String,
        role: String
    ) {
        val userSession = UserSession(activity)
        userSession.save(UserSession.SHARED_PREFERENCE_USERNAME_KEY, username)
        userSession.save(UserSession.SHARED_PREFERENCE_NAME_KEY,name)
        userSession.save(UserSession.SHARED_PREFERENCE_EMAIL_KEY,email)
        userSession.save(UserSession.SHARED_PREFERENCE_PASSWORD_KEY,password)
        userSession.save(UserSession.SHARED_PREFERENCE_ROLE_KEY,role)
    }
}