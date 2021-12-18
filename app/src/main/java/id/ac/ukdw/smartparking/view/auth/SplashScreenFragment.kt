package id.ac.ukdw.smartparking.view.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import id.ac.ukdw.smartparking.databinding.FragmentSplashScreenBinding
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_PASSWORD_KEY
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_ROLE_KEY
import id.ac.ukdw.smartparking.extentions.UserSession.Companion.SHARED_PREFERENCE_USERNAME_KEY
import id.ac.ukdw.smartparking.view.main.PengelolaDashboardActivity
import id.ac.ukdw.smartparking.view.main.PengunjungDashboardActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding
    private var isLogin: Boolean = false
    private var username: String = ""
    private var password: String = ""
    private var role: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return bindView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkSessions()
        navigateToDashboard()
    }

    private fun checkSessions() {
        val preferences = UserSession(requireActivity())
        username = preferences
            .getValueString(SHARED_PREFERENCE_USERNAME_KEY)
        if (!username.isNullOrEmpty()){
            isLogin = true
        }
        if(isLogin){
            role = preferences.getValueString(SHARED_PREFERENCE_ROLE_KEY)
            password = preferences
                .getValueString(SHARED_PREFERENCE_PASSWORD_KEY)
        }
    }

    private fun bindView(): View {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        return  binding.root
    }

    private fun navigateToDashboard() {
        if(isLogin){
            if (role == "pengelola"){
                Handler().postDelayed({
                    val intent = Intent(requireActivity(), PengelolaDashboardActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                },3000)
            } else if(role == "pengunjung"){
                Handler().postDelayed({
                    val intent = Intent(requireActivity(), PengunjungDashboardActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                },3000)
            }
        } else {
            val direction = SplashScreenFragmentDirections
                .actionSplashScreenFragmentToLoginFragment()
            Handler().postDelayed({findNavController().navigate(direction)}, 3000)
        }
    }
}