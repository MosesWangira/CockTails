package com.mosesaltruism.cocktails.core.common.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.mosesaltruism.cocktails.CockTails
import com.mosesaltruism.cocktails.R
import com.mosesaltruism.cocktails.core.common.helper.DataStorePreference
import com.mosesaltruism.cocktails.databinding.DashBoardBinding
import com.mosesaltruism.cocktails.presentation.byname.views.Home
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dash_board.*
import javax.inject.Inject

@AndroidEntryPoint
class DashBoard: AppCompatActivity() {
    lateinit var binding: DashBoardBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var preferences: DataStorePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Change back theme to normal theme
         * */
        setTheme(R.style.Theme_CockTails)

        binding = DataBindingUtil.setContentView(
            this,
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

    private fun setUpDarkOrLightMode(){
        if (preferences.searchedCocktailName.equals("yes")) {
            Toast.makeText(applicationContext, "Night mode", Toast.LENGTH_SHORT).show()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            Toast.makeText(applicationContext, "Light mode", Toast.LENGTH_SHORT).show()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onStart() {
        super.onStart()

        //setUpDarkOrLightMode()
    }
}