package id.ac.ukdw.smartparking

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.smartparking.databinding.ActivityDashboardPengurusBinding

class DashboardPengurus : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityDashboardPengurusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardPengurusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Write a message to the database
        database = Firebase.database.reference

        val databaseListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get data dari firebase dan update value ke tampilan UI

                val parkiranA = dataSnapshot.child("parkiran/A").getValue<Boolean>().toString()
                val parkiranB = dataSnapshot.child("parkiran/B").getValue<Boolean>().toString()
                val parkiranC = dataSnapshot.child("parkiran/C").getValue<Boolean>().toString()
                val parkiranD = dataSnapshot.child("parkiran/D").getValue<Boolean>().toString()
                val pemasukan = dataSnapshot.child("pemasukan/desember/1").getValue<Double>().toString()

                val pengunjung = dataSnapshot.child("pengunjung").getValue<Double>().toString()
                binding.tvFirebase.text = "Pengunjung: $pengunjung\nParkiran A: $parkiranA\nPemasukan Desember Tanggal 1: Rp $pemasukan"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(databaseListener)
    }
}