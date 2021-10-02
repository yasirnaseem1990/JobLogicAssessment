package com.joblogic.assessment.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joblogic.assessment.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_call,
                R.id.navigation_buy,
                R.id.navigation_sell
            )
        )

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_call -> {
                    navController.navigate(
                        R.id.navigation_call,
                        null,
                        navOptions {
                            popUpTo(R.id.navigation_call)
                            { inclusive = true }
                        }
                    )
                    true
                }
                R.id.navigation_buy -> {
                    navController.navigate(
                        R.id.navigation_buy,
                        null,
                        navOptions {
                            popUpTo(R.id.navigation_buy)
                            { inclusive = true }
                        })
                    true

                }
                R.id.navigation_sell -> {
                    navController.navigate(
                        R.id.navigation_sell,
                        null,
                        navOptions {
                            popUpTo(R.id.navigation_sell)
                            { inclusive = true }
                        })
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.setOnNavigationItemReselectedListener { }
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}