package id.ac.ukdw.smartparking.view.pengunjung

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.view.adapter.RiwayatAdapter
import id.ac.ukdw.smartparking.view.adapter.RiwayatViewPagerAdapter
import id.ac.ukdw.smartparking.view.main.AuthActivity


class PengunjungDashboardFragment : Fragment() {
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
//        profile(view)
    }

    private fun tabLayoutRiwayat(view: View) {
        val listFragment: ArrayList<Fragment> = arrayListOf(PengunjungRiwayatParkirFragment(),PengunjungRiwayatSaldoFragment())
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
                    tab.text = "Saldo"
                }
            }
        }.attach()
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

//    private fun profile(view: View) {
//        val btnProfile = view.findViewById<ImageView>(R.id.btnProfile)
//        btnProfile.setOnClickListener {
//            var bottomFragment = PengunjungProfileFragment()
//            bottomFragment.setStyle(DialogFragment.STYLE_NORMAL, Color.TRANSPARENT)
//            bottomFragment.show(getParentFragmentManager(), "TAG")
//        }
//    }

    fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
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
}