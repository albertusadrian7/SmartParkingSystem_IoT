package id.ac.ukdw.smartparking.view.pengunjung

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.smartparking.view.adapter.RiwayatAdapter
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungRiwayatParkirBinding
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_ID_KEY
import id.ac.ukdw.smartparking.model.kartu.GetKartuItem
import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem
import id.ac.ukdw.smartparking.presenter.ListRiwayatPresenter
import id.ac.ukdw.smartparking.view.viewInterface.KartuInterface
import id.ac.ukdw.smartparking.view.viewInterface.RiwayatInterface
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class PengunjungRiwayatParkirFragment : Fragment(), RiwayatInterface {
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
        ListRiwayatPresenter(requireActivity(), this).getHistoryPresenter(getIdUser())
    }

    override fun onStart() {
        super.onStart()
        ListRiwayatPresenter(requireActivity(), this).getHistoryPresenter(getIdUser())
    }

    private fun getIdUser(): String {
        val preferences = UserSession(requireActivity())
        val idUser = preferences.getValueString(SHARED_PREFERENCE_ID_KEY)
        Log.d(TAG,idUser)
        return idUser
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

    override fun showDataRiwayat(riwayat: List<GetParkingSessionItem>) {
        updateDataRiwayat(riwayat)
    }

    override fun updateDataRiwayat(riwayat: List<GetParkingSessionItem>) {
        riwayatAdapter.setData(riwayat as ArrayList<GetParkingSessionItem>)
    }

    override fun resultSuccess(result: List<GetParkingSessionItem>) {
        showDataRiwayat(result)
    }

    override fun resultFailed(t: Throwable) {
        Toast.makeText(requireContext(),"Pesan: $t", Toast.LENGTH_LONG).show()
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

}