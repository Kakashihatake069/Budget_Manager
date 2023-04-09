package com.example.budgettracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.budgettracker.R
import com.example.budgettracker.databinding.ActivityMainBinding
import com.example.budgettracker.databinding.ActivityReportdataBinding

class ReportdataActivity : AppCompatActivity() {
    lateinit var binding: ActivityReportdataBinding
    lateinit var imgreportback : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportdata)
        binding = ActivityReportdataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

//        imgreportback = findViewById(R.id.imgreportback)
        binding.imgreportback.setOnClickListener {
            var report = Intent(this, MainActivity::class.java)
            startActivity(report)
        }
    }
}