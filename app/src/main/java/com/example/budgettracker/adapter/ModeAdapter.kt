package com.example.budgettracker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.R

class ModeAdapter(var modelist: ArrayList<String>,var m : ((String)-> Unit)) : RecyclerView.Adapter<ModeAdapter.MyViewHolder>() {
    var noss= -1
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var txtitemmode: RadioButton = itemview.findViewById(R.id.txtitemmode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.modeitemfile,parent,false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtitemmode.text =modelist[position]

        holder.txtitemmode.setOnClickListener {
            m.invoke(modelist[position])
            Log.e("TAG", "working: "+modelist[position])
            noss=position
            notifyDataSetChanged()
        }
        if (position==noss)
        {
            holder.txtitemmode.isChecked=true
        }
        else{
            holder.txtitemmode.isChecked=false
        }

    }
    override fun getItemCount(): Int {
        return modelist.size
    }
}