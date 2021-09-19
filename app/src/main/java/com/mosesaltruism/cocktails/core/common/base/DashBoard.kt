package com.mosesaltruism.cocktails.core.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.mosesaltruism.cocktails.R
import com.mosesaltruism.cocktails.databinding.DashBoardBinding

class DashBoard : AppCompatActivity() {
    lateinit var binding: DashBoardBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Change back theme to normal theme
         * */
        setTheme(R.style.Theme_CockTails)

        binding = DataBindingUtil.setContentView(this,
            R.layout.dash_board
        )

        val navController = this.findNavController(R.id.navHostFragment)

        /**
         * Handle app bar configurations in navigation drawer
         * */
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    /**
     * Handle up arrow if its not first level navigation
     * */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}