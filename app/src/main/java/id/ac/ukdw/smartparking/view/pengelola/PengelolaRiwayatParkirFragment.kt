package id.ac.ukdw.smartparking.view.pengelola

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
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungDashboardBinding
import id.ac.ukdw.smartparking.view.adapter.RiwayatAdapter
import id.ac.ukdw.smartparking.view.main.AuthActivity
import android.R
import android.graphics.Color
import androidx.fragment.app.DialogFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungRiwayatParkirBinding


class PengelolaRiwayatParkirFragment : Fragment() {
    private lateinit var binding: FragmentPengunjungRiwayatParkirBinding
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
        riwayatAdapter = RiwayatAdapter(arrayListOf())
        rvRiwayat = binding.rvRiwayatParkir
        rvRiwayat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        rvRiwayat.setAdapter(riwayatAdapter)
    }

    private fun bindingView(): View {
        binding = FragmentPengunjungRiwayatParkirBinding.inflate(layoutInflater)
        return binding.root
    }

}