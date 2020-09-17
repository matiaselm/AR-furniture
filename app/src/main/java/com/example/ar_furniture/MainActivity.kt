package com.example.ar_furniture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), onFurnitureSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, FurnitureListFragment.newInstance())
                .commit()
        }
    }

    override fun onFurnitureSelected(furniture: Furniture, position: Int) {
        val furnitureArFragment = FurnitureArActivity.newInstance(furniture)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, furnitureArFragment, "furnitureinar")
            .addToBackStack(null)
            .commit()
    }

}
