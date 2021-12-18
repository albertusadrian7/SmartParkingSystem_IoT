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
        inputRupiah()
        btnTopUpSaldo()
    }

    private fun inputRupiah() {
        binding.etJumlahTopUp.addTextChangedListener(object : TextWatcher {
            var setEditText = binding.etJumlahTopUp
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!s.toString().equals(current)) {
                    setEditText.removeTextChangedListener(this)

                    val cleanString: String = s!!.replace("""[Rp,. ]""".toRegex(), "")
                    nominal = cleanString
                    val formatted = NumberFormat.getCurrencyInstance(Locale("id","ID")).format(cleanString.replace("[^\\d]".toRegex(),"").toLong())

                    current = formatted
                    setEditText.setText(formatted)
                    setEditText.setSelection(formatted.length)
                    setEditText.addTextChangedListener(this)
                }
            }
        })

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
        binding.btnSubmit.setOnClickListener {
            Toast.makeText(requireContext(),"Jumlah Top Up: $nominal",Toast.LENGTH_LONG).show()
            topUp(getIdUser(),nominal)
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