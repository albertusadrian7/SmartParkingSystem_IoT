package id.ac.ukdw.smartparking.view.viewInterface

interface TopUpInterface {
    fun topUp(
        id_user: String,
        nominal: String
    )
    fun onTopUpSuccess(message: String)
    fun onTopUpFail(message: String)
}