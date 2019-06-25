package com.arildojr.nubank.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arildojr.nubank.R
import android.content.Intent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
