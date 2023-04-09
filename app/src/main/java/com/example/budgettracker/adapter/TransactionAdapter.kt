package com.example.budgettracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.R
import com.example.budgettracker.Valuemodel

class TransactionAdapter(var datastorage: ArrayList<Valuemodel>, var invo: (Valuemodel) -> Unit, var delete : ((Int)->Unit)) :RecyclerView.Adapter<TransactionAdapter.MyViewHolder>(){
    class MyViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {

        var txtid : TextView = itemview.findViewById(R.id.txtid)
        var txtamount : TextView = itemview.findViewById(R.id.txtamount)
        var txtnote : TextView = itemview.findViewById(R.id.txtnote)
        var txtcategory : TextView = itemview.findViewById(R.id.txtcategory)
        var txttype : TextView= itemview.findViewById(R.id.txttype)
        var txtdate : TextView = itemview.findViewById(R.id.txtdate)
        var txttime : TextView = itemview.findViewById(R.id.txttime)
        var txtmode : TextView = itemview.findViewById(R.id.txtmode)
        var imgedit : ImageView = itemview.findViewById(R.id.imgedit)
        var imgdelete : ImageView = itemview.findViewById(R.id.imgdelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.storageitem,null,false)
        val adapter = MyViewHolder(view)
        return adapter
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtamount.setText(datastorage[position].amount)
        holder.txtnote.setText(datastorage[position].note)
        holder.txtcategory.setText(datastorage[position].categoryselect)
        holder.txttype.setText(datastorage[position].type.toString())
        holder.txtdate.setText(datastorage[position].adddate)
        holder.txttime.setText(datastorage[position].addtime)
        holder.txtmode.setText(datastorage[position].selectmode)
        holder.txtid.setText(datastorage[position].id.toString())


        holder.imgedit.setOnClickListener {
            invo.invoke(datastorage[position])

        }

        holder.imgdelete.setOnClickListener {
            delete.invoke(datastorage[position].id)

        }

    }

    override fun getItemCount(): Int {
       return datastorage.size
    }

    fun updatedData(datastorage: ArrayList<Valuemodel>){
        this.datastorage = ArrayList()
        this.datastorage.addAll(datastorage)
        notifyDataSetChanged()

    }
}