package id.ac.ukdw.smartparking.view.pengelola

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.databinding.FragmentPengelolaKartuBinding
import id.ac.ukdw.smartparking.presenter.RegisterUserCardPresenter
import id.ac.ukdw.smartparking.view.viewInterface.RegisterUserCardInterface


class PengelolaKartuFragment : BottomSheetDialogFragment(), RegisterUserCardInterface {
    private lateinit var binding: FragmentPengelolaKartuBinding
    private lateinit var cardUid: String
    private lateinit var idUser: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_pengunjung_saldo,container,false)
//        return view
        val view = bindingView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerKartu()
    }

    private fun registerKartu() {
        binding.btnSubmit.setOnClickListener {
            cardUid = binding.etIdKartu.text.toString()
            idUser = binding.etIdPengguna.text.toString()
            registerUserCard(cardUid,idUser)
        }
    }

    private fun bindingView(): View {
        binding = FragmentPengelolaKartuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        findNavController().navigate(R.id.dashboardFragment)
    }


    override fun registerUserCard(card_uid: String, id_user: String) {
        RegisterUserCardPresenter(requireActivity(),this)
            .registerUserCardPresenter(
                card_uid,
                id_user
            )
    }

    override fun onRegisterUserCardSuccess(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
        dialog!!.dismiss()
    }

    override fun onRegisterUserCardFail(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
        dialog!!.dismiss()
    }

}