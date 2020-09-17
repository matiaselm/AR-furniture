package com.example.ar_furniture

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


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
        val intent = Intent(this, FurnitureArActivity)
        val b = Bundle()
        b.putSerializable("furn", furniture)
        intent.putExtras(b)
        startActivity(intent)
        finish()
    }

}
