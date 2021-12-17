package id.ac.ukdw.smartparking.view.viewInterface

interface RegisterInterface {
    fun registerPengunjung(
        id_user: String,
        username: String,
        email: String,
        password: String,
        name: String,
        role: String
    )
    fun onRegisterSuccess(message: String)
    fun onRegisterFail(message: String)
}