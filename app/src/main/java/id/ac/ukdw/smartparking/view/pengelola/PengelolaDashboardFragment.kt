package id.ac.ukdw.smartparking.view.pengelola

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
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.databinding.FragmentPengelolaDashboardBinding
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.view.adapter.Penghasilan
import id.ac.ukdw.smartparking.view.adapter.RiwayatPenghasilanHarianAdapter
import id.ac.ukdw.smartparking.view.main.AuthActivity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
        setCardDashboard()
//        profile(view)
    }

    private fun setCardDashboard() {
        database = Firebase.database.reference

        var date = Date()
        val formatBulan = SimpleDateFormat("MMMM")
        val bulan: String = formatBulan.format(date)
        val formatTanggal = SimpleDateFormat("dd")
        val tanggal: String = formatTanggal.format(date)
//        Toast.makeText(requireContext(), bulan+" "+tanggal, Toast.LENGTH_LONG).show()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val jumlahPengunjung = dataSnapshot.child("pemasukan/"+bulan+"/"+tanggal+"/pengunjung").getValue<Int>()
                val jumlahPenghasilan = dataSnapshot.child("pemasukan/"+bulan+"/"+tanggal+"/total").getValue<Double>()
                binding.tvJumlahPengunjung.text = jumlahPengunjung.toString()+" Orang"
                binding.tvJumlahPenghasilan.text = rupiah(jumlahPenghasilan!!)+",-"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)
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
        database = FirebaseDatabase.getInstance().getReference("pemasukan/December")
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

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

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