package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.canine_care.R
import kotlinx.android.synthetic.main.activity_theme.*


class ThemeActivity : AppCompatActivity() {
    private  val TAG = "ThemeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        btg_theme.addOnButtonCheckedListener { _, selectedBtnId, isChecked ->
            if (isChecked) {
                val theme = when (selectedBtnId) {
                    R.id.btnDefault -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    R.id.btnDark -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_NO
                }
                Log.d(TAG, "isChecked:$isChecked theme:$theme")
                //                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -- default
                //                AppCompatDelegate.MODE_NIGHT_YES -dark
                //                AppCompatDelegate.MODE_NIGHT_NO - light
                AppCompatDelegate.setDefaultNightMode(theme)
            }
        }

    }
}