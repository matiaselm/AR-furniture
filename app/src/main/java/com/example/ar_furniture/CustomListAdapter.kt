package com.example.ar_furniture

import android.app.Activity
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.ar_furniture.Tag.tag
import kotlinx.android.synthetic.main.color_list_item.view.*

class CustomListAdapter(private val context: Activity, private val imgid: ArrayList<String>): ArrayAdapter<String>(
    context,
    R.layout.color_list_item,
    imgid
) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View{
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.color_list_item, null, true)

        when(imgid[position]){
            "valkoinen" -> rowView.color_box.setBackgroundColor(Color.WHITE)
            "musta" -> rowView.color_box.setBackgroundColor(Color.BLACK)
            "punainen" -> rowView.color_box.setBackgroundColor(Color.RED)
            "harmaa" -> rowView.color_box.setBackgroundColor(Color.GRAY)
            else -> {
                rowView.color_box.visibility = View.GONE
                rowView.color_info.text = "default"
            }
        }

        return rowView
    }
}