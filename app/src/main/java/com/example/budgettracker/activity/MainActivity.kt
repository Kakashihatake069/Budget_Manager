package com.example.budgettracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import com.example.budgettracker.R
import com.example.budgettracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initview()

    }
    private fun initview() {

        binding.drawermenu.setOnClickListener {
            binding.navigationdrawer.openDrawer(GravityCompat.START)
        }

         val tilte_income = "Add Income"
        binding.crdincome.setOnClickListener {
            var i = Intent(this, IncomeActivity::class.java)
            i.putExtra("page","income")
            i.putExtra("title",tilte_income)
            startActivity(i)
        }
       val title_expence = "Add Expence"
        binding.crdexpense.setOnClickListener {
            var e = Intent(this, IncomeActivity::class.java)
            e.putExtra("page","expence")
            e.putExtra("title",title_expence)
            startActivity(e)
        }

        binding.crdtrans.setOnClickListener {
            var t = Intent(this, TransactionActivity::class.java)
            startActivity(t)
        }
        binding.crdreports.setOnClickListener {
            var r = Intent(this, ReportdataActivity::class.java)
            startActivity(r)
        }
        binding.crdcategory.setOnClickListener {
            var c = Intent(this, CategoryActivity::class.java)
            startActivity(c)
        }

    }


}