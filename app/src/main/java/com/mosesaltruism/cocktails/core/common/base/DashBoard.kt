package com.mosesaltruism.cocktails.core.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mosesaltruism.cocktails.R

class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Change back theme to normal theme
         * */
        setTheme(R.style.Theme_CockTails)
        setContentView(R.layout.dash_board)
    }
}