package id.ac.ukdw.smartparking.view.viewInterface

interface RegisterUserCardInterface {
    fun registerUserCard(
        card_uid: String,
        id_user: String
    )
    fun onRegisterUserCardSuccess(message: String)
    fun onRegisterUserCardFail(message: String)
}