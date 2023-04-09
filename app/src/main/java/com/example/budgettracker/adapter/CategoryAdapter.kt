package com.example.budgettracker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.ModelClass
import com.example.budgettracker.R

class CategoryAdapter(var addlist: ArrayList<ModelClass>, var n : ((String)-> Unit)) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
var nos= -1
class MyViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){

        var rditemcategory: RadioButton = itemview.findViewById(R.id.rditemcategory)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.categoryitemfile,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.rditemcategory.text =addlist[position].name

        holder.rditemcategory.setOnClickListener {
            n.invoke(addlist[position].name)

            Log.e("TAG", "onBindViewHolder: "+addlist[position].name)
            nos = position
            notifyDataSetChanged()
        }

        if (position==nos)
        {
            holder.rditemcategory.isChecked=true
        }else
        {
          holder.rditemcategory.isChecked=false
        }
    }

    override fun getItemCount(): Int {
    return addlist.size
    }
//    fun updatedate(list: ArrayList<ModelClass>) {
//        this.addlist=list
//        notifyDataSetChanged()
//
//    }
}