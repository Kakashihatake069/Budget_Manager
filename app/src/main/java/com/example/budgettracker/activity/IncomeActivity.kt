package com.example.budgettracker.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgettracker.*
import com.example.budgettracker.adapter.CategoryAdapter
import com.example.budgettracker.adapter.ModeAdapter
import com.example.budgettracker.databinding.ActivityCategoryBinding
import com.example.budgettracker.databinding.ActivityIncomeBinding
import com.example.budgettracker.databinding.IncomeCategoryDialogboxBinding
import com.example.budgettracker.databinding.ModeDialogboxBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class IncomeActivity : AppCompatActivity() {

    lateinit var imginback : ImageView
    lateinit var selectcategory : TextView
    lateinit var selectmodetext : TextView
    lateinit var rgInEx : RadioGroup
    lateinit var imgdone : TextView
    lateinit var edtamount : EditText
    lateinit var edtnote : EditText
    lateinit var crdcategory : CardView
    lateinit var rbincome : RadioButton
    lateinit var rbexpence : RadioButton
    lateinit var txttitleincome : TextView
    lateinit var cdmode : CardView
    lateinit var incomehelper : DatabaseHelperClass
    lateinit var adapter : CategoryAdapter
    lateinit var adaptermode : ModeAdapter
    var addlist = ArrayList<ModelClass>()
    var datastorage = ArrayList<Valuemodel>()
    var modelist = ArrayList<String>()

    var type = -1
    lateinit var binding: ActivityIncomeBinding
        var categoryselect = ""
    var selectmode = ""

    var adddate = ""
    var addtime = ""
    var storage_id= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_income)
        binding = ActivityIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        incomehelper = DatabaseHelperClass(this)

        initview()


    }



    @SuppressLint("SuspiciousIndentation")
    private fun initview()
    {

        imgdone = findViewById(R.id.imgdone)
        edtnote = findViewById(R.id.edtnote)
        edtamount = findViewById(R.id.edtamount)
        selectcategory = findViewById(R.id.selectcategory)
        selectmodetext = findViewById(R.id.selectmodetext)
        rbincome = findViewById(R.id.rbincome)
        cdmode = findViewById(R.id.cdmode)
        rbexpence = findViewById(R.id.rbexpence)
        txttitleincome = findViewById(R.id.txttitleincome)
        imginback = findViewById(R.id.imginback)
        crdcategory = findViewById(R.id.crdcategory)
        rgInEx= findViewById(R.id.rgInEx)


        if (intent!=null  && intent.hasExtra("updateRecord"))
        {
            var newamt : String? = intent.getStringExtra("amount")
            var notenew : String? = intent.getStringExtra("note")
            var newtitle : String? = intent.getStringExtra("title")
            var newdonebtn : String? = intent.getStringExtra("update")
            storage_id = intent.getIntExtra("id",0)
//            var datenew : String? = intent.getStringExtra("adddate")
//            var timenew : String? = intent.getStringExtra("addtime")

            binding.edtamount.setText(newamt)
            binding.edtnote.setText(notenew)
            binding.txttitleincome.setText(newtitle)
            binding.imgdone.setText(newdonebtn)


        }

//        imgdone.setOnClickListener{
//            var a
//        }
        binding.imginback.setOnClickListener {
            var back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }

        binding.crdcategory.setOnClickListener {
            val dialog = Dialog(this)
            val dialogBinding : IncomeCategoryDialogboxBinding = IncomeCategoryDialogboxBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            addlist = incomehelper.displaydata(addlist)
//            dialog.setCancelable(false)
//            dialog.setContentView(R.layout.income_category_dialogbox)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



//            val btncancel : AppCompatButton = dialog.findViewById(R.id.btncancel)
//            val btnset : AppCompatButton = dialog.findViewById(R.id.btnset)
//

            dialogBinding.btnset.setOnClickListener {
                Toast.makeText(this,"Your category is set",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialogBinding.btncancel.setOnClickListener {
                Toast.makeText(this,"Are you sure you want Cancel",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

           adapter = CategoryAdapter(addlist,{name ->
              categoryselect = name

            selectcategory.text=name
               Log.e("TAG", "working: "+categoryselect )
           })
            var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                dialogBinding.rcvCategory.layoutManager=manager
            dialogBinding.rcvCategory.adapter= adapter

            dialog.dismiss()
            dialog.show()


        }

        binding.cdmode.setOnClickListener {
            val dialogmode = Dialog(this)
            val dialogBinding : ModeDialogboxBinding = ModeDialogboxBinding.inflate(layoutInflater)
            dialogmode.setContentView(dialogBinding.root)

            dialogmode.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val btncancel : AppCompatButton = dialogmode.findViewById(R.id.btnmodecancel)
            val btnset : AppCompatButton = dialogmode.findViewById(R.id.btnmodeset)

            btnset.setOnClickListener {
                Toast.makeText(this,"Your category is set",Toast.LENGTH_SHORT).show()
                dialogmode.dismiss()
            }

            btncancel.setOnClickListener {
                Toast.makeText(this,"Are you sure you want Cancel",Toast.LENGTH_SHORT).show()
                dialogmode.dismiss()
            }


            adaptermode = ModeAdapter(modelist,{modelist ->
                selectmode=modelist
                selectmodetext.text=modelist
                Log.e("TAG", "working: "+selectmode )
            })
            var modeManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                dialogBinding.rcvmode.layoutManager=modeManager
            dialogBinding.rcvmode.adapter = adaptermode

            dialogmode.dismiss()
            dialogmode.show()

        }




        binding.imgdone.setOnClickListener {



            var amount = binding.edtamount.text.toString()
            if(amount.isEmpty()){
                Toast.makeText(this,"Please enter amount",Toast.LENGTH_SHORT).show()
            }
            else if(amount.length <=1 || amount.length>=10)
            {
                Toast.makeText(this,"Please enter valid Input",Toast.LENGTH_SHORT).show()
            }
            Log.e("Tag","working:"+amount)



            var note = binding.edtnote.text.toString()
            if(note.isEmpty()){
                Toast.makeText(this,"Please enter Note",Toast.LENGTH_SHORT).show()
            }
            else if(note.length <=1 || note.length>=20)
            {
                Toast.makeText(this,"Please enter valid Input",Toast.LENGTH_SHORT).show()

            } else {


                if (binding.rgInEx.checkedRadioButtonId == -1) {
                    Toast.makeText(applicationContext, "Please Select Transaction", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedId: Int = binding.rgInEx.checkedRadioButtonId
                    var selectedRadioButton: RadioButton = findViewById(selectedId)
                    var text = selectedRadioButton.text.toString()

                    if (text.equals("Income")) {
                        type = 1
                    } else {
                        type = 2
                    }
                    Log.e("TAG", "working: " + type)

                }
            }
            if (txttitleincome.text.toString().equals("update details"))
            {
                if(binding.imgdone.text.toString().equals("update"))
                    incomehelper.updaterecord(amount,note,storage_id)
            }
                else {
                incomehelper.insertincomeExpencedata(
                        type,
                        amount,
                        categoryselect,
                        adddate,
                        addtime,
                        selectmode,
                        note
                    )

                }

           var done =Intent(this@IncomeActivity, MainActivity::class.java)
            startActivity(done)
        }


        val page = intent.getStringExtra("page")
        val name : String? = intent.getStringExtra("title")
        when(page){
            "income"->
                binding.rbincome.isChecked = true

            "expence"->
                binding.rbexpence.isChecked = true

        }
        binding.txttitleincome.text=name





        val TextView : TextView = findViewById(R.id.txtdate)

        val simpleDateFormat = SimpleDateFormat("dd.mm.yyyy")
        val currentDataAndTime:String = simpleDateFormat.format(Date())
        TextView.text = currentDataAndTime
         adddate = currentDataAndTime

        val textv : TextView = findViewById(R.id.txttime)

        val simpleTimeFormat = SimpleDateFormat("HH.mm")
        val currentTime: String = simpleTimeFormat.format(Date())
        textv.text = currentTime
         addtime = currentTime

        Log.e("TAG", "initview: "+adddate)





        modelist.add("Cash")
        modelist.add("Credit Card")
        modelist.add("Debit Card")
        modelist.add("UPI")
        modelist.add("Net Banking")



    }
}