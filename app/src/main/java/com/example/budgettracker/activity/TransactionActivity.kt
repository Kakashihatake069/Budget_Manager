package com.example.budgettracker.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.DatabaseHelperClass
import com.example.budgettracker.R
import com.example.budgettracker.adapter.TransactionAdapter
import com.example.budgettracker.Valuemodel
import com.example.budgettracker.databinding.ActivityMainBinding
import com.example.budgettracker.databinding.ActivityTransactionBinding
import com.example.budgettracker.databinding.DeleteDialogboxBinding

class TransactionActivity : AppCompatActivity() {
    lateinit var binding: ActivityTransactionBinding
    lateinit var imgtransback: ImageView
    lateinit var db: DatabaseHelperClass
    lateinit var adapter: TransactionAdapter
    var datastorage = ArrayList<Valuemodel>()
    lateinit var rcvtrans: RecyclerView
    lateinit var imgdelete: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelperClass(this)

        initview()
    }

    private fun initview() {

        binding.imgtransback.setOnClickListener {
            var transback = Intent(this, MainActivity::class.java)
            startActivity(transback)
        }

        datastorage = db.displayincomeExpencedata()
        var title = "update details"
        var donebtn = "update"
        db.displayincomeExpencedata()


        adapter = TransactionAdapter(datastorage, {
            val transactions = Intent(this@TransactionActivity, IncomeActivity::class.java)
            transactions.putExtra("amount", it.amount)
            transactions.putExtra("id", it.id)
            transactions.putExtra("note", it.note)
            transactions.putExtra("adddate", it.adddate)
            transactions.putExtra("addtime", it.addtime)
            transactions.putExtra("selectmode", it.selectmode)
            transactions.putExtra("categoryselect", it.categoryselect)
            transactions.putExtra("title", title)
            transactions.putExtra("update", donebtn)
            transactions.putExtra("updateRecord", true)
            startActivity(transactions)
            Log.e(
                "TAG",
                "putextra: " + it.amount + "" + it.note + " " + it.adddate + " " + it.addtime + " " + it.selectmode + " " + it.categoryselect
            )

        }, { id ->


            val dialog = Dialog(this)
            val dialogBinding: DeleteDialogboxBinding =
                DeleteDialogboxBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            dialogBinding.btndeletecancel.setOnClickListener {
                Toast.makeText(this, "Your Transaction has been Canceled", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
            }

            dialogBinding.btndelete.setOnClickListener {

                db.deleteData(id)
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                datastorage = db.displayincomeExpencedata()
                adapter.updatedData(datastorage)
                Log.e("TAG", "deleted record" + id)

                Toast.makeText(this, "Your Transaction has been Deleted", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }




            dialog.show()
        })

        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvtrans.layoutManager = manager
        binding.rcvtrans.adapter = adapter


        datastorage = db.displayincomeExpencedata()
        adapter.updatedData(datastorage)


//        adapter = TransactionAdapter( on )

    }
}