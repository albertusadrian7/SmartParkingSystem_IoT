package id.ac.ukdw.smartparking.view.pengunjung

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
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungSaldoBinding
import java.text.NumberFormat
import java.util.*


class PengunjungSaldoFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPengunjungSaldoBinding
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
    }

    private fun inputRupiah() {
        binding.etJumlahTopUp.addTextChangedListener(object : TextWatcher {
            var setEditText = binding.etJumlahTopUp
            var current: String = ""
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().equals(current)) {
                    setEditText.removeTextChangedListener(this)

                    val cleanString: String = s!!.replace("""[Rp,. ]""".toRegex(), "")

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
}