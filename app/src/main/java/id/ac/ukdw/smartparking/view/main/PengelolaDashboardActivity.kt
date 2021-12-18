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
import id.ac.ukdw.smartparking.view.pengelola.PengelolaKartuFragment


class PengelolaDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengelola_dashboard)

        supportActionBar?.hide()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.dashboardFragment,
            R.id.kartuFragment,
            R.id.parkirFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboardFragment -> {
                    navController.navigate(R.id.dashboardFragment)
                }
                R.id.kartuFragment -> {
                    var bottomFragment = PengelolaKartuFragment()
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

}