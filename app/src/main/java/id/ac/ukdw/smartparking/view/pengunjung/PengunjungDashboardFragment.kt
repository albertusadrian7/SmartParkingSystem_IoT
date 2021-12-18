package id.ac.ukdw.smartparking.view.pengunjung

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.model.kartu.GetKartuItem
import id.ac.ukdw.smartparking.presenter.KartuPresenter
import id.ac.ukdw.smartparking.view.adapter.RiwayatAdapter
import id.ac.ukdw.smartparking.view.adapter.RiwayatViewPagerAdapter
import id.ac.ukdw.smartparking.view.main.AuthActivity
import id.ac.ukdw.smartparking.view.viewInterface.KartuInterface
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class PengunjungDashboardFragment : Fragment(), KartuInterface {
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var rvRiwayat: RecyclerView
    private lateinit var riwayatAdapter: RiwayatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengunjung_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLightStatusBar(view)
        tabLayoutRiwayat(view)
        logOut(view)
        KartuPresenter(requireActivity(),this, view).getKartuPresenter(getIdUser())
//        profile(view)
    }

    private fun tabLayoutRiwayat(view: View) {
        val listFragment: ArrayList<Fragment> = arrayListOf(PengunjungRiwayatParkirFragment(),PengunjungRiwayatTopUpFragment())
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayoutRiwayat)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerRiwayat)
        val riwayatViewPagerAdapter = RiwayatViewPagerAdapter(listFragment,this)
        viewPager.adapter = riwayatViewPagerAdapter
        TabLayoutMediator(tabLayout,viewPager){tab,position->
            when(position){
                0->{
                    tab.text = "Parkir"
                }
                1->{
                    tab.text = "Top Up"
                }
            }
        }.attach()
    }

    private fun getIdUser(): String {
        val preferences = UserSession(requireActivity())
        val idUser = preferences.getValueString(UserSession.SHARED_PREFERENCE_ID_KEY)
        Log.d(ContentValues.TAG,idUser)
        return idUser
    }

    private fun logOut(view: View) {
        val btnKeluar = view.findViewById<ImageView>(R.id.btnProfile)
        btnKeluar.setOnClickListener {
            val preferences = UserSession(requireActivity())
            preferences.clearSharedPreference()
            val intent = Intent(requireActivity(), AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
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

    override fun resultCardSuccess(kartu: List<GetKartuItem>, view: View) {
        if (!kartu[0].cardUid.isNullOrEmpty()) {
            val gaPunyaKartu = view.findViewById<ConstraintLayout>(R.id.gaPunyaKartu)
            gaPunyaKartu.visibility = View.GONE
            val tvIdKartu = view.findViewById<TextView>(R.id.tvIdKartu)
            tvIdKartu.text = kartu[0].cardUid.toString()
            val tvJumlahSaldo = view.findViewById<TextView>(R.id.tvJumlahSaldo)
            tvJumlahSaldo.text = kartu[0].saldo?.let { rupiah(it.toDouble()) }
        }
    }

    override fun resultCardFailed(t: Throwable) {
        Toast.makeText(requireContext(),"Pesan: $t", Toast.LENGTH_LONG).show()
    }
}