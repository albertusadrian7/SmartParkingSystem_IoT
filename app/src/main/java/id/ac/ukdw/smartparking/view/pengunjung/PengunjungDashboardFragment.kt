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
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungDashboardBinding
import id.ac.ukdw.smartparking.view.adapter.RiwayatAdapter
import id.ac.ukdw.smartparking.view.main.AuthActivity
import android.R
import android.util.Log
import android.widget.Toast
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.model.kartu.GetKartuItem
import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem
import id.ac.ukdw.smartparking.presenter.KartuPresenter
import id.ac.ukdw.smartparking.presenter.ListRiwayatPresenter
import id.ac.ukdw.smartparking.view.viewInterface.KartuInterface
import id.ac.ukdw.smartparking.view.viewInterface.RiwayatInterface
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "ID USER"
class PengunjungDashboardFragment : Fragment(),RiwayatInterface,KartuInterface {
    private lateinit var binding: FragmentPengunjungDashboardBinding
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
        setLightStatusBar(view)
        setRecyclerViewRiwayat()
        // Refresh Data Produk
        onStart()
        ListRiwayatPresenter(requireActivity(), this).getHistoryPresenter(getIdUser())
        KartuPresenter(requireActivity(),this).getKartuPresenter(getIdUser())
    }

    override fun onStart() {
        super.onStart()
        ListRiwayatPresenter(requireActivity(), this).getHistoryPresenter(getIdUser())
    }

    fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    private fun setRecyclerViewRiwayat() {
        rvRiwayat = binding.rvRiwayat
        rvRiwayat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        riwayatAdapter = RiwayatAdapter(arrayListOf())
        rvRiwayat.adapter = riwayatAdapter
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
        binding = FragmentPengunjungDashboardBinding.inflate(layoutInflater)
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

    private fun getIdUser(): String {
        val preferences = UserSession(requireActivity())
        val idUser = preferences.getValueString(UserSession.SHARED_PREFERENCE_ID_KEY)
        Log.d(TAG,idUser)
        return idUser
    }

    override fun resultCardSuccess(kartu: List<GetKartuItem>) {
        binding.tvIdKartu.text = kartu[0].cardUid.toString()
        binding.tvJumlahSaldo.text = kartu[0].saldo?.let { rupiah(it.toDouble()) }
    }

    override fun resultCardFailed(t: Throwable) {
        Toast.makeText(requireContext(),"Pesan: $t",Toast.LENGTH_LONG).show()
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}