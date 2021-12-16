package id.ac.ukdw.smartparking
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.ukdw.smartparking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        navigateToDashboardPengurus()
    }

//    private fun navigateToDashboardPengurus(){
//        binding.btnLogin.setOnClickListener {
//            val intent = Intent(this, DashboardPengurus::class.java)
//            startActivity(intent)
//        }
//    }

}