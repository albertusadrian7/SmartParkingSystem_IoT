package id.ac.ukdw.smartparking.view.main

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.fragment.app.DialogFragment.STYLE_NO_TITLE
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.view.pengunjung.PengunjungSaldoFragment
import android.content.DialogInterface





class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengunjung_dashboard)

        supportActionBar?.hide()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.dashboardFragment,
            R.id.saldoFragment,
            R.id.parkirFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboardFragment -> {
                    navController.navigate(R.id.dashboardFragment)
                }
                R.id.saldoFragment -> {
                    var bottomFragment = PengunjungSaldoFragment()
                    bottomFragment.setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
                    bottomFragment.show(supportFragmentManager, "TAG")
                }
                R.id.parkirFragment -> {
                    navController.navigate(R.id.parkirFragment)
                }
            }
            true
        }

//        setupNav()

    }

    private fun setupNav() {
        val navController = findNavController(R.id.fragmentContainerView)
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.dashboardFragment -> showBottomNav()
                R.id.saldoFragment -> {
                    Toast.makeText(this,"Tombol telah berhasil diklik",Toast.LENGTH_SHORT).show()
                    // on below line we are creating a new bottom sheet dialog.
                    val dialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)

                    // on below line we are inflating a layout file which we have created.
                    val view = layoutInflater.inflate(R.layout.fragment_pengunjung_test, null)

                    // on below line we are creating a variable for our button
                    // which we are using to dismiss our dialog.
                    val btnClose = view.findViewById<Button>(R.id.btnMasuk)

                    // on below line we are adding on click listener
                    // for our dismissing the dialog button.
                    btnClose.setOnClickListener {
                        // on below line we are calling a dismiss
                        // method to close our dialog.
                        dialog.dismiss()
                    }
                    // below line is use to set cancelable to avoid
                    // closing of dialog box when clicking on the screen.
                    dialog.setCancelable(true)

                    // on below line we are setting
                    // content view to our view.
                    dialog.setContentView(view)

                    // on below line we are calling
                    // a show method to display a dialog.
                    dialog.show()
                    showBottomNav()
                }
                R.id.parkirFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.visibility = View.GONE
    }

}