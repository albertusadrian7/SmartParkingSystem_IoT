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
import java.text.NumberFormat
import java.util.*


class PengelolaKartuFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPengelolaKartuBinding
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
    }

    private fun bindingView(): View {
        binding = FragmentPengelolaKartuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        findNavController().navigate(R.id.dashboardFragment)
    }
}