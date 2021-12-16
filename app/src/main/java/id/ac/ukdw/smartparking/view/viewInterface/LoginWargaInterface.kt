package id.ac.ukdw.smartparking.view.viewInterface

import android.app.Activity

interface LoginWargaInterface {
    fun loginWarga(email: String,password: String)
    fun onLoginSuccess(message: String)
    fun onLoginFail(message: String)
}