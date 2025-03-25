package com.o7solutions.androidkotlin5_6pmonline.listpackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.o7solutions.androidkotlin5_6pmonline.R

class ListBaseAdapter(var arrayList: ArrayList<Student>):BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=LayoutInflater.from(parent?.context).inflate(R.layout.list_item,parent,false)

        var name=view.findViewById<TextView>(R.id.tvName)
        return view
    }
}