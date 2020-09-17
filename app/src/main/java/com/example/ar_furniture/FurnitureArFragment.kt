package com.example.ar_furniture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.sceneform.ux.ArFragment

class FurnitureArFragment: ArFragment() {
    lateinit var furniture: Furniture

    companion object {
        private const val MODEL = "model"

        fun newInstance(furniture: Furniture): FurnitureArFragment {
            val args = Bundle()
            args.putSerializable(MODEL, furniture)
            val fragment = FurnitureArFragment()
            fragment.arguments = args
            return fragment
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        furniture = arguments!!.getSerializable(MODEL) as Furniture
        return super.onCreateView(inflater, container, savedInstanceState)
        // furniture.src on tiedostonimi
    }

}