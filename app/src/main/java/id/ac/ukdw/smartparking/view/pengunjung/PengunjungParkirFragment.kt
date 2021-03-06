package id.ac.ukdw.smartparking.view.pengunjung

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungParkirBinding
import id.ac.ukdw.smartparking.view.main.AuthActivity

class PengunjungParkirFragment : Fragment() {
    private lateinit var binding: FragmentPengunjungParkirBinding
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var database: DatabaseReference

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
        setSlotParkir()

    }

    fun setSlotParkir() {
        // Write a message to the database
        database = Firebase.database.reference

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val slotA = dataSnapshot.child("parkiran/A").getValue<Boolean>()
                val slotB = dataSnapshot.child("parkiran/B").getValue<Boolean>()
                val slotC = dataSnapshot.child("parkiran/C").getValue<Boolean>()
                val slotD = dataSnapshot.child("parkiran/D").getValue<Boolean>()

                // set slot A
                if(slotA == true) {
                    binding.bgA.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary_green))
                } else {
                    binding.bgA.setBackgroundColor(Color.RED)
                }
                // set slot B
                if(slotB == true) {
                    binding.bgB.setBackgroundColor(getResources().getColor(R.color.primary_green))
                } else {
                    binding.bgB.setBackgroundColor(Color.RED)
                }

                // set slot C
                if(slotC == true) {
                    binding.bgC.setBackgroundColor(getResources().getColor(R.color.primary_green))
                } else {
                    binding.bgC.setBackgroundColor(Color.RED)
                }

                // set slot D
                if(slotD == true) {
                    binding.bgD.setBackgroundColor(getResources().getColor(R.color.primary_green))
                } else {
                    binding.bgD.setBackgroundColor(Color.RED)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)
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
        binding = FragmentPengunjungParkirBinding.inflate(layoutInflater)
        return binding.root
    }

}