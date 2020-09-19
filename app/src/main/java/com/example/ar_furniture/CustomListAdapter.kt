package com.example.ar_furniture

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.color_list_item.view.*

class CustomListAdapter(private val context: Activity, private val imgid: ArrayList<String>): ArrayAdapter<String>(context, R.layout.color_list_item, imgid) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View{
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.color_list_item, null, true)

        rowView.color_text.text = imgid[position]

        return rowView
    }
}