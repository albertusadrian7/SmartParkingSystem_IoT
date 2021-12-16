package id.ac.ukdw.smartparking.view.warga.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.ac.ukdw.smartparking.databinding.FragmentWargaLoginBinding
import id.ac.ukdw.smartparking.extentions.UserValidator
import id.ac.ukdw.smartparking.view.main.AuthActivity
import id.ac.ukdw.smartparking.view.viewInterface.LoginWargaInterface

class WargaLoginFragment : Fragment() {
    private lateinit var binding: FragmentWargaLoginBinding
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var email: String
    private lateinit var password: String
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
        etUsername = binding.etUsername
        etPassword = binding.etPassword
        emailFocusListener()
        passwordFocusListener()
        binding.btnMasuk.setOnClickListener {
            binding.etUsername.clearFocus()
            binding.etPassword.clearFocus()
            submitForm()
        }
    }

    private fun submitForm() {
        val validEmail = !binding.etUsername.text.isNullOrEmpty()
        val validPassword = !binding.etPassword.text.isNullOrEmpty()

        if (validEmail && validPassword){
            Log.d("TAG", "Email: $email; password: $password;")
//            loginWarga(email,password)
        } else {
            if (!validEmail){
                binding.emailUserContainer.helperText = "Masukan email!"
            }
            if (!validPassword){
                binding.passUserContainer.helperText = "Masukan password!"
            }
            Toast.makeText(requireContext(),"Login gagal!",Toast.LENGTH_LONG).show()
        }
    }

    private fun emailFocusListener() {
        binding.etUsername.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.emailUserContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        email = binding.etUsername.text.toString().trim()
        var isValidEmail = UserValidator.isEmailValid(email)
        if (email.isEmpty()){
            return "Masukan email!"
        }
        if (!isValidEmail){
            return "Email tidak valid!"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.etPassword.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.passUserContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        password = binding.etPassword.text.toString()
        if (password.isEmpty()){
            return "Masukan password!"
        }
        return null
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

    private fun navigateToRegisterPage() {
        binding.tvDaftar.setOnClickListener {
            val direction = WargaLoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(direction)
        }
    }

    private fun bindingView(): View {
        binding = FragmentWargaLoginBinding.inflate(layoutInflater)
        return binding.root
    }


}