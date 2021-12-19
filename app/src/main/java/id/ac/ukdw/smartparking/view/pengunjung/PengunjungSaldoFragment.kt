package id.ac.ukdw.smartparking.view.pengunjung

import android.content.ContentValues
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungSaldoBinding
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.presenter.RegisterPresenter
import id.ac.ukdw.smartparking.presenter.TopUpPresenter
import id.ac.ukdw.smartparking.view.viewInterface.TopUpInterface
import java.text.NumberFormat
import java.util.*


class PengunjungSaldoFragment : BottomSheetDialogFragment(),TopUpInterface {
    private lateinit var binding: FragmentPengunjungSaldoBinding
    private lateinit var nominal: String
    private var current: String = ""

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
        btnTopUpSaldo()
    }

    private fun bindingView(): View {
        binding = FragmentPengunjungSaldoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        findNavController().navigate(R.id.dashboardFragment)
    }

    private fun btnTopUpSaldo(){
        nominal = binding.etJumlahTopUp.text.toString()
        binding.btnSubmit.setOnClickListener {
            Toast.makeText(requireContext(),"Jumlah Top Up: $nominal",Toast.LENGTH_LONG).show()
            topUp(getIdUser(),nominal)
            dialog!!.dismiss()
        }
    }

    override fun topUp(id_user: String, nominal: String) {
        TopUpPresenter(requireActivity(),this)
            .topUpPresenter(
                id_user,
                nominal
            )
    }

    override fun onTopUpSuccess(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    override fun onTopUpFail(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    private fun getIdUser(): String {
        val preferences = UserSession(requireActivity())
        val idUser = preferences.getValueString(UserSession.SHARED_PREFERENCE_ID_KEY)
        Log.d(ContentValues.TAG,idUser)
        return idUser
    }

}