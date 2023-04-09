package com.example.budgettracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.budgettracker.R
import com.example.budgettracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    lateinit var crdincome :CardView
//    lateinit var crdexpense : CardView
//    lateinit var crdtrans : CardView
//    lateinit var crdreports : CardView
//    lateinit var crdcategory : CardView
//    lateinit var tilte_income : TextView
//    lateinit var title_expence : TextView

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initview()

    }

    private fun initview() {
//        crdincome = findViewById(R.id.crdincome)
//        crdexpense = findViewById(R.id.crdexpense)
//        crdtrans = findViewById(R.id.crdtrans)
//        crdreports = findViewById(R.id.crdreports)
//        crdcategory = findViewById(R.id.crdcategory)
//        tilte_income = findViewById(R.id.tilte_income)
//        title_expence = findViewById(R.id.title_expence)


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