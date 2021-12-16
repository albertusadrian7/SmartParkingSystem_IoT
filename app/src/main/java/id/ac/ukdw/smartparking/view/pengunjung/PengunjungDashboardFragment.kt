package id.ac.ukdw.smartparking.view.pengunjung

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungDashboardBinding
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungMarketBinding
import id.ac.ukdw.smartparking.view.main.AuthActivity

class PengunjungDashboardFragment : Fragment() {
    private lateinit var binding: FragmentPengunjungDashboardBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = bindingView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

}