package id.ac.ukdw.smartparking.view.pengelola

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.databinding.FragmentPengelolaDashboardBinding
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.view.adapter.Penghasilan
import id.ac.ukdw.smartparking.view.adapter.RiwayatPenghasilanHarianAdapter
import id.ac.ukdw.smartparking.view.main.AuthActivity


class PengelolaDashboardFragment : Fragment() {
    private lateinit var binding: FragmentPengelolaDashboardBinding
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var database: DatabaseReference

    private lateinit var rvRiwayatPEnghasilanHarian: RecyclerView
    private lateinit var listPenghasilan: ArrayList<Penghasilan>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = bindingView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLightStatusBar(view)
        setRecyclerView()
        logOut(view)
//        profile(view)
    }

    private fun setRecyclerView() {
        rvRiwayatPEnghasilanHarian = binding.rvRiwayatParkir
        rvRiwayatPEnghasilanHarian.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        rvRiwayatPEnghasilanHarian.setHasFixedSize(true)

        listPenghasilan = arrayListOf<Penghasilan>()
        getDataPenghasilan()
//        rvRiwayat.setAdapter(riwayatAdapter)
    }

    private fun getDataPenghasilan() {
        database = FirebaseDatabase.getInstance().getReference("pemasukan/desember")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listPenghasilan.clear()
                if(snapshot.exists()) {
                    for(penghasilanSnapshot in snapshot.children) {
                        val penghasilan = penghasilanSnapshot.getValue(Penghasilan::class.java)
                        listPenghasilan.add(penghasilan!!)
                    }
                    rvRiwayatPEnghasilanHarian.adapter = RiwayatPenghasilanHarianAdapter(listPenghasilan)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
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

    private fun bindingView(): View {
        binding = FragmentPengelolaDashboardBinding.inflate(layoutInflater)
        return binding.root
    }
}