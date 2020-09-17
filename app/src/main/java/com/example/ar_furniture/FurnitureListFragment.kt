package com.example.ar_furniture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_furniture_list.*
import kotlinx.android.synthetic.main.recycler_item_furniture.view.*
import java.lang.ClassCastException

class FurnitureListFragment : Fragment() {

    private lateinit var listener: onFurnitureSelected
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewAdapter: FurnitureRecyclerAdapter

    companion object {
        fun newInstance() = FurnitureListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is onFurnitureSelected) {
            listener = context
        } else {
            throw ClassCastException(
                "$context must implement OnUserSelected"
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(com.example.ar_furniture.R.layout.fragment_furniture_list, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
    }

    fun setAdapter() {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = FurnitureRecyclerAdapter(FurnitureList.lista, listener)

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }

   internal inner class FurnitureRecyclerAdapter(private val furnitures: ArrayList<Furniture>, private var clickListener: onFurnitureSelected) : RecyclerView.Adapter<FurnitureRecyclerAdapter.FurnitureViewHolder>() {
       internal inner class FurnitureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
           private val type: TextView = view.type
           private val name: TextView = view.name

           fun initialize(furniture: Furniture, action: onFurnitureSelected) {
               type.text = furniture.type
               name.text = furniture.name
               itemView.setOnClickListener(){
                   action.onFurnitureSelected(furniture, adapterPosition)
               }

           }
       }

       override fun onCreateViewHolder(
           parent: ViewGroup,
           viewType: Int
       ): FurnitureRecyclerAdapter.FurnitureViewHolder {
           val view = LayoutInflater.from(parent.context)
               .inflate(com.example.ar_furniture.R.layout.recycler_item_furniture, parent, false)
           return FurnitureViewHolder(view)
       }

       override fun onBindViewHolder(
           holder: FurnitureRecyclerAdapter.FurnitureViewHolder,
           position: Int
       ) {
           val furniture = furnitures[position]
           holder.initialize(furniture, listener)
       }

       override fun getItemCount() = furnitures.count()
       }

   }
interface onFurnitureSelected {
    fun onFurnitureSelected(furniture: Furniture, position: Int)
}
