package com.example.budgettracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.DatabaseHelperClass
import com.example.budgettracker.ModelClass
import com.example.budgettracker.R
import com.example.budgettracker.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity()
{
    lateinit var binding: ActivityCategoryBinding
    lateinit var db: DatabaseHelperClass

    var addlist = ArrayList<ModelClass>()
    lateinit var Rcvcategory : RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = DatabaseHelperClass(this,)

//        adapter = CategoryAdapter()
//        var LayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        binding.Rcvcategory.layoutManager=LayoutManager
//        binding.Rcvcategory.adapter= adapter
        initview()
    }

    private fun initview() {
           binding.btnsubmit.setOnClickListener {
                val name = binding.edtcategory.text.toString()

               db.insertData(name)
                addlist = db.displaydata(addlist)
//               adapter.updatedate(addlist)

               var submit = Intent(this@CategoryActivity, MainActivity::class.java)
               startActivity(submit)
           }
        binding.imgcategoryback.setOnClickListener {
            var cback = Intent (this, MainActivity::class.java)
            startActivity(cback)
        }

    }
}