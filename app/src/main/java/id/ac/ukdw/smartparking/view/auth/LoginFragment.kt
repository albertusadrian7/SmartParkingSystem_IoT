package id.ac.ukdw.smartparking.view.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.ac.ukdw.smartparking.databinding.FragmentLoginBinding
import id.ac.ukdw.smartparking.presenter.LoginPresenter
import id.ac.ukdw.smartparking.view.main.AuthActivity
import id.ac.ukdw.smartparking.view.main.PengunjungDashboardActivity
import id.ac.ukdw.smartparking.view.viewInterface.LoginInterface

class LoginFragment : Fragment(), LoginInterface {
    private lateinit var binding: FragmentLoginBinding
    private var username: String = ""
    private var password: String = ""
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = bindingView()
        navigateToRegisterPage()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLightStatusBar(view)
        usernameFocusListener()
        passwordFocusListener()
        binding.btnMasuk.setOnClickListener {
            binding.etUsername.clearFocus()
            binding.etPassword.clearFocus()
            submitForm()
        }
    }

    fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    private fun navigateToDashboard() {
        Thread.sleep(1000)
        val intent = Intent(
            requireActivity(),
            AuthActivity::class.java
        )
        startActivity(intent)
        requireActivity().finish()
    }

    private fun submitForm(){
        val validUsername = !binding.etUsername.text.isNullOrEmpty()
        val validPassword = !binding.etPassword.text.isNullOrEmpty()
        if (validUsername && validPassword){
            loginPengunjung(username,password)
        } else {
            if (!validUsername){
                binding.usernameContainer.helperText = "Masukan username!"
            }
            if (!validPassword){
                binding.passUserContainer.helperText = "Masukan password!"
            }
            Toast.makeText(requireContext(),"Login gagal!",Toast.LENGTH_LONG).show()
        }
    }

    private fun usernameFocusListener() {
        binding.etUsername.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.usernameContainer.helperText = validUsername()
            }
        }
    }

    private fun passwordFocusListener() {
        binding.etPassword.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.passUserContainer.helperText = validPassword()
            }
        }
    }

    private fun validUsername(): String? {
        username = binding.etUsername.text.toString().trim()
        if (username.isEmpty()){
            return "Masukan username!"
        }
        return null
    }

    private fun validPassword(): String? {
        password = binding.etPassword.text.toString().trim()
        if (password.isEmpty()){
            return "Masukan password!"
        }
        return null
    }

    private fun navigateToRegisterPage() {
        binding.tvDaftar.setOnClickListener {
            val direction = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(direction)
        }
    }

    private fun bindingView(): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun loginPengunjung(username: String, password: String) {
        LoginPresenter(requireActivity(),this)
            .loginPengunjung(
                username,
                password
            )
    }

    override fun onLoginSuccess(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        navigateToDashboard()
    }

    override fun onLoginFail(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }


}