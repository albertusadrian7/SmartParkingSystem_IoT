package id.ac.ukdw.smartparking.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import android.os.Build
import android.util.Log
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Toast
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.databinding.FragmentRegisterBinding
import id.ac.ukdw.smartparking.extentions.UserValidator


class WargaRegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private var kodeKeluarga: String = ""
    private var email: String = ""
    private var password: String = ""
    private var konfirmasiPassword: String = ""
    private var nama: String = ""
    private var gender: String = ""
    private var noHP: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = bindingView()
        return view
    }

    fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    private fun bindingView(): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setFormJenisKelamin()
        navigateToLoginPage()
        setLightStatusBar(view)
        nameFocusListener()
        emailFocusListener()
        passwordFocusListener()
        confirmPasswordFocusListener()
        binding.btnRegisterWarga.setOnClickListener {
            binding.etNamaLengkap.clearFocus()
            binding.etEmail.clearFocus()
            binding.etPassword.clearFocus()
            binding.etKonfirmasiPassword.clearFocus()
            submitForm()
        }
    }

    private fun submitForm(){
        val validName = !binding.etNamaLengkap.text.isNullOrEmpty()
        val validEmail = !binding.etEmail.text.isNullOrEmpty()
        val validPassword = !binding.etPassword.text.isNullOrEmpty()
        val validConfirmPassword = !binding.etKonfirmasiPassword.text.isNullOrEmpty()
        val validGender = !gender.isNullOrEmpty()
        if (validName && validEmail && validPassword && validConfirmPassword && validGender){
            if (password != konfirmasiPassword){
                binding.confirmPasswordContainer.helperText = "Password tidak sesuai!"
            } else {
                Log.d("TAG", "kode keluarga: $kodeKeluarga; Email: $email; password: $password; nama: $nama; gender: $gender")
//                registerWarga(kodeKeluarga,email,password,nama,gender,noHP)
            }
        } else {
            if (!validName){
                binding.namaContainer.helperText = "Masukan nama!"
            }
            if (!validEmail){
                binding.emailContainer.helperText = "Masukan email!"
            }
            if (!validPassword){
                binding.passwordContainer.helperText = "Masukan password!"
            }
            if (!validConfirmPassword){
                binding.confirmPasswordContainer.helperText = "Masukan konfirmasi password!"
            }
            Toast.makeText(requireContext(),"Registrasi gagal!",Toast.LENGTH_LONG).show()
        }
    }


    private fun nameFocusListener(){
        binding.etNamaLengkap.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.namaContainer.helperText = validName()
            }
        }
    }


    private fun emailFocusListener(){
        binding.etEmail.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun passwordFocusListener(){
        binding.etPassword.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun confirmPasswordFocusListener(){
        binding.etKonfirmasiPassword.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.confirmPasswordContainer.helperText = validConfirmPassword()
            }
        }
    }

    private fun validConfirmPassword(): String? {
        konfirmasiPassword = binding.etKonfirmasiPassword.text.toString().trim()
        if (konfirmasiPassword.isEmpty()){
            return "Masukan konfirmasi password!"
        }
        return null
    }

    private fun validEmail(): String?{
        email = binding.etEmail.text.toString().trim()
        var isValidEmail = UserValidator.isEmailValid(email)
        if (email.isEmpty()){
            return "Masukan email!"
        }
        if (!isValidEmail){
            return "Email tidak valid!"
        }
        return null
    }

    private fun validName():String? {
        nama = binding.etNamaLengkap.text.toString().trim()
        var isValidName = UserValidator.isNameValid(nama)
        var isNameIncludeLetters = UserValidator.isNameIncludeLetters(nama)
        if (nama.isEmpty()){
            return "Masukan nama!"
        }
        if (!isValidName){
            if (!isNameIncludeLetters){
                return "Nama harus mengandung karakter!"
            }
        }
        return null
    }

    private fun validPassword(): String?{
        password = binding.etPassword.text.toString()
        var isPasswordValid = UserValidator.isPasswordsValid(password)
        var isPasswordLengthValid = UserValidator.passwordLengthValidator(password)
        var isPasswordWithNum = UserValidator.isPasswordWithNumber(password)
        var isPasswordIncludeUppercase = UserValidator.isPasswordIncludeUppercase(password)
        var isPasswordIncludeLetters = UserValidator.isPasswordIncludeLetters(password)
        if (password.isEmpty()){
            return "Masukan password!"
        }
        if (!isPasswordValid){
            if (!isPasswordLengthValid){
                return "Password panjangnya min. 8 karakter"
            }
            if (!isPasswordIncludeUppercase){
                return "Password harus mengandung min. 1 huruf kapital"
            }
            if (!isPasswordWithNum){
                return "Password harus mengandung min. 1 angka"
            }
            if (!isPasswordIncludeLetters){
                return "Password harus mengandung min. 1 huruf kecil"
            }
        }
        return null
    }

    private fun navigateToLoginPage() {
        binding.tvMasuk.setOnClickListener {
            val direction = WargaRegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(direction)
        }
    }


}