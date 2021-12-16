//package id.ac.ukdw.smartparking.presenter
//
//import android.app.Activity
//import android.util.Log
//import android.widget.Toast
//import com.maluku.sma_rt.R
//import com.maluku.sma_rt.api.RetrofitService
//import com.maluku.sma_rt.extentions.UserSession
//import com.maluku.sma_rt.extentions.UserValidator
//import com.maluku.sma_rt.model.warga.CreateWargaResponse
//import com.maluku.sma_rt.view.viewInterface.RegisterWargaInterface
//import id.ac.ukdw.smartparking.R
//import id.ac.ukdw.smartparking.api.RetrofitService
//import id.ac.ukdw.smartparking.extentions.UserSession
//import id.ac.ukdw.smartparking.extentions.UserValidator
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//private const val TAG = "REGISTER PRESENTER"
//class WargaRegisterPresenter(private val activity: Activity, private val view: RegisterWargaInterface) {
//    //register new user
//    fun registerWarga(
//        kodeKeluarga: String,
//        email: String,
//        password: String,
//        name: String,
//        gender: String,
//        noHP: String) {
//        when (UserValidator.verifyData(email, password, name)) {
//            true -> {
//                pushToCloud(kodeKeluarga, email, password, name, gender, noHP)
//            }
//        }
//    }
//
//    //push new data to cloud so user can register to server
//    private fun pushToCloud(
//        kodeKeluarga: String,
//        email: String,
//        password: String,
//        name: String,
//        gender: String,
//        noHP: String
//    ) {
//        RetrofitService
//            .getService()
//            .signUpWarga(
//                kodeKeluarga,
//                name,
//                email,
//                password,
//                noHP,
//                gender)
//            .enqueue(object : Callback<CreateWargaResponse> {
//                override fun onResponse(
//                    call: Call<CreateWargaResponse>,
//                    response: Response<CreateWargaResponse>
//                ) {
//                    val token = response.body()?.token.toString()
//                    when(response.isSuccessful){
//                        true -> {
//                            when(token.isNotEmpty()) {
//                                true -> saveSession(
//                                    kodeKeluarga,
//                                    name,
//                                    email,
//                                    password,
//                                    gender,
//                                    noHP,
//                                    token
//                                )
//                            }
//                            Toast.makeText(activity,"Pesan: ${response.body()?.message.toString()}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<CreateWargaResponse>, t: Throwable) {
//                    Log.i(TAG, "onFailure: ${t.message}")
//                }
//            })
//    }
//
//    //save user data to session
//    private fun saveSession(
//        kodeKeluarga: String,
//        name: String,
//        email: String,
//        password: String,
//        gender: String,
//        noHP: String,
//        token: String
//    ) {
//        val userSession = UserSession(activity)
//        userSession.save(UserSession.SHARED_PREFERENCE_TOKEN_KEY,token)
//        userSession.save(UserSession.SHARED_PREFERENCE_NAME_KEY,name)
//        userSession.save(UserSession.SHARED_PREFERENCE_EMAIL_KEY,email)
//        userSession.save(UserSession.SHARED_PREFERENCE_PASSWORD_KEY,password)
//        userSession.save(UserSession.SHARED_PREFERENCE_GENDER_KEY,gender)
//        userSession.save(UserSession.SHARED_PREFERENCE_PHONE_NUMBER_KEY,noHP)
//        userSession.save(UserSession.SHARED_PREFERENCE_FAMILY_ID_KEY,kodeKeluarga)
////        view.onRegisterSuccess(activity.getString(R.string.login_sukses))
//    }
//}