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
import id.ac.ukdw.smartparking.view.adapter.RiwayatAdapter
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungRiwayatSaldoBinding


class PengunjungRiwayatSaldoFragment : Fragment() {
    private lateinit var binding: FragmentPengunjungRiwayatSaldoBinding
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var rvRiwayat: RecyclerView
    private lateinit var riwayatAdapter: RiwayatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = bindingView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        riwayatAdapter = RiwayatAdapter()
        rvRiwayat = binding.rvRiwayatSaldo
        rvRiwayat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        rvRiwayat.setAdapter(riwayatAdapter)
    }

    private fun bindingView(): View {
        binding = FragmentPengunjungRiwayatSaldoBinding.inflate(layoutInflater)
        return binding.root
    }

}