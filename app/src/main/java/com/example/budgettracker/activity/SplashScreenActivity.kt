package com.example.budgettracker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.budgettracker.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val logo = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(logo)
            finish()
        }, 4000)
    }
}