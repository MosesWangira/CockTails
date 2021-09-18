package com.mosesaltruism.cocktails.core.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mosesaltruism.cocktails.R

class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dash_board)
    }
}