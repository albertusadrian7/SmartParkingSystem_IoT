package id.ac.ukdw.smartparking.view.pengunjung

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungSaldoBinding
import id.ac.ukdw.smartparking.view.adapter.RiwayatAdapter
import id.ac.ukdw.smartparking.view.adapter.RiwayatSaldoAdapter
import id.ac.ukdw.smartparking.view.main.AuthActivity

class PengunjungSaldoFragment : Fragment() {
    private lateinit var binding: FragmentPengunjungSaldoBinding
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var rvRiwayatSaldo: RecyclerView
    private lateinit var riwayatSaldoAdapter: RiwayatSaldoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = bindingView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLightStatusBar(view)
        setRecyclerView()
    }

    fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    private fun setRecyclerView() {
        riwayatSaldoAdapter = RiwayatSaldoAdapter()
        rvRiwayatSaldo = binding.rvRiwayatSaldo
        rvRiwayatSaldo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        rvRiwayatSaldo.setAdapter(riwayatSaldoAdapter)
    }

    private fun navigateToHomePage() {
        Thread.sleep(1000)
        val intent = Intent(
            requireActivity(),
            AuthActivity::class.java
        )
        startActivity(intent)
        requireActivity().finish()
    }

    private fun bindingView(): View {
        binding = FragmentPengunjungSaldoBinding.inflate(layoutInflater)
        return binding.root
    }

}