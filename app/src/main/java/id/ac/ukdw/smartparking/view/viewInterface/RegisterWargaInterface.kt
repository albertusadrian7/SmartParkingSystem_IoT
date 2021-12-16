package id.ac.ukdw.smartparking.view.viewInterface

interface RegisterWargaInterface {
    fun registerWarga(
        kodeKeluarga: String,
        email: String,
        password: String,
        name: String,
        gender: String,
        noHP: String
    )
    fun onRegisterSuccess(message: String)
}