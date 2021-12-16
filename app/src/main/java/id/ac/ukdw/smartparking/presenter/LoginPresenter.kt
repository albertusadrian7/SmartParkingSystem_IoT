//package id.ac.ukdw.smartparking.presenter
//
//import android.app.Activity
//import android.util.Log
//import android.widget.Toast
//import com.maluku.sma_rt.model.login.OnLoginSuccessResponse
//import com.maluku.sma_rt.view.viewInterface.LoginWargaInterface
//import id.ac.ukdw.smartparking.R
//import id.ac.ukdw.smartparking.api.RetrofitService
//import id.ac.ukdw.smartparking.extentions.UserSession
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//private const val TAG = "LOGIN PRESENTER"
//class WargaLoginPresenter(private val activity: Activity, private val view: LoginWargaInterface) {
//    //login warga
//    fun loginWargaPresenter(
//        email: String,
//        password: String
//    ) {
//        pushToCloud(email, password)
//    }
//
//    //push new data to cloud so user can register to server
//    private fun pushToCloud(
//        email: String,
//        password: String
//    ) {
//        RetrofitService
//            .getService()
//            .loginWarga(
//                email,
//                password
//            )
//            .enqueue(object : Callback<OnLoginSuccessResponse> {
//                override fun onResponse(
//                    call: Call<OnLoginSuccessResponse>,
//                    response: Response<OnLoginSuccessResponse>
//                ) {
//                    val token = response.body()?.token.toString()
//                    when(response.isSuccessful){
//                        true -> {
//                            when(token.isNotEmpty()) {
//                                true -> saveSession(
//                                    email,
//                                    password,
//                                    token
//                                )
//                            }
//                            Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
//                        }
//                        false -> {
//                            Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<OnLoginSuccessResponse>, t: Throwable) {
////                    view.onLoginFail(activity.getString(R.string.login_gagal))
//                    Log.i(TAG, "onFailure: ${t.message}")
//                }
//            })
//    }
//
//    //save user data to session
//    private fun saveSession(
//        email: String,
//        password: String,
//        token: String
//    ) {
//        val adminSession = UserSession(activity)
//        adminSession.save(UserSession.SHARED_PREFERENCE_TOKEN_KEY,token)
//        adminSession.save(UserSession.SHARED_PREFERENCE_EMAIL_KEY,email)
//        adminSession.save(UserSession.SHARED_PREFERENCE_PASSWORD_KEY,password)
////        view.onLoginSuccess(activity.getString(R.string.login_sukses))
//    }
//
//}