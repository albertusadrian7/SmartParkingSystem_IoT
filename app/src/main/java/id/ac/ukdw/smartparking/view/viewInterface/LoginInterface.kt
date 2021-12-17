package id.ac.ukdw.smartparking.view.viewInterface


interface LoginInterface {
    fun loginPengunjung(username: String,password: String)
    fun onLoginSuccess(message: String)
    fun onLoginFail(message: String)
}