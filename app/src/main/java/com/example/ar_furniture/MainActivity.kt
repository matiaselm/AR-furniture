package com.example.ar_furniture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.HitTestResult
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.sceneform.ux.TransformableNode

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
        val furnitureArFragment = FurnitureArFragment.newInstance(furniture)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, furnitureArFragment, "furnitureinar")
            .addToBackStack(null)
            .commit()
    }

}
