package id.ac.ukdw.smartparking.extentions

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class UserSession(activity: Activity) {
    private var sharedPreferences: SharedPreferences
    private val SHARED_PREFERENCE_CODE = "LOGIN SESSION"

    init {
        sharedPreferences = activity
            .getSharedPreferences(
                SHARED_PREFERENCE_CODE,
                Context.MODE_PRIVATE
            )
    }

    fun save(key: String,value: String){
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }
    fun save(key: String,value: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean(key,value)
        editor.apply()
    }
    fun getValueString(key: String): String{
        return sharedPreferences.getString(key,"").toString()
    }
    fun getValueBoolean(key: String): Boolean{
        return sharedPreferences.getBoolean(key,false)
    }
    fun clearSharedPreference() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
    fun removeValue(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    companion object {
        const val SHARED_PREFERENCE_ID_KEY = "ID CODE"
        const val SHARED_PREFERENCE_USERNAME_KEY = "USERNAME CODE"
        const val SHARED_PREFERENCE_EMAIL_KEY = "EMAIL CODE"
        const val SHARED_PREFERENCE_PASSWORD_KEY = "PASSWORD CODE"
        const val SHARED_PREFERENCE_NAME_KEY = "NAME CODE"
        const val SHARED_PREFERENCE_ROLE_KEY = "ROLE CODE"
        const val SHARED_PREFERENCE_SESSION_KEY = "SESSION CODE"
    }
}