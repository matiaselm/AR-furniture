package com.example.ar_furniture

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.HitTestResult
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.fragment_ar.*


class FurnitureArActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var furniture: Furniture
    private lateinit var arFragment: ArFragment
    private var testRenderable: ModelRenderable? = null
    private var currentContext: Context = this
    private var colorArray = ArrayList<String>()
    private var key = "musta"

    private fun addObj(hitResult: HitResult, plane: Plane, motionEvent: MotionEvent) {
        if (testRenderable == null) {
            Log.d("AR", "testRenderable == null")
            return
        }
        val anchor = hitResult!!.createAnchor()
        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(arFragment.arSceneView.scene)
        val viewNode = TransformableNode(arFragment.transformationSystem)
        viewNode.setParent(anchorNode)
        viewNode.renderable = testRenderable
        viewNode.select()
        viewNode.setOnTapListener { hitTestRes: HitTestResult?, motionEv: MotionEvent? ->
            removeNode(viewNode, motionEv)
        }
    }

    private fun removeNode(nodeToRemove: TransformableNode, motionEv: MotionEvent?) {
        Log.d("spinner","MotionEvent: $motionEv")

        when(remove_button.visibility){
            View.VISIBLE -> remove_button.visibility = View.GONE
            View.GONE -> remove_button.visibility = View.VISIBLE
            else -> {
                remove_button.visibility = View.GONE
            }
        }

        //Remove a selected Node
        remove_button.setOnClickListener{
            arFragment.getArSceneView().getScene().removeChild(nodeToRemove);
            nodeToRemove.setParent(null);
            nodeToRemove.renderable = null
            remove_button.visibility = View.GONE
        }

        //Toast.makeText(currentContext, "Ouch!!!!", Toast.LENGTH_SHORT).show()

    }

    private fun goBack() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initSpinner() {
        Log.d("spinner", "initSpinner")
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            colorArray
        ).also { adapter ->
            Log.d("spinner", "adapter")
            // Specify the layout
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Log.d("spinner", "adapter 2")
            // Apply the adapter to the spinner
            color_picker.adapter = adapter
        }

        color_picker.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        val key = parent.getItemAtPosition(pos).toString()
        Log.d("spinner", "Item selected: ${key}")
        renderModel(key)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        Log.d("spinner", "Nothing selected, ${parent}")
    }

    private fun renderModel(key: String) {
        // furniture.src[key] palauttaa tiedostonimen
        val modelUri = Uri.parse(furniture.src[key])
        Log.d("spinner", "rendering model: $modelUri")

        val renderableFuture = ModelRenderable.builder()
            .setSource(
                currentContext, RenderableSource.builder().setSource(
                    currentContext,
                    modelUri,
                    RenderableSource.SourceType.GLTF2
                )
                    .setScale(0.1f)
                    .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                    .build()
            )
            .setRegistryId(key)
            .build()

        renderableFuture.thenAccept { it -> testRenderable = it }

        renderableFuture.exceptionally { throwable ->
            Toast.makeText(currentContext, "Unable to create renderable", Toast.LENGTH_SHORT).show()
            null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_ar)

        colorArray.add("musta")
        colorArray.add("valkoinen")

        val b = this.intent.extras
        if (b != null) {
            furniture = b.getSerializable("furn") as Furniture
        }

        arFragment = supportFragmentManager.findFragmentById(R.id.furniture_fragment) as ArFragment

        renderModel(key)

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            addObj(hitResult, plane, motionEvent)
        }

        back_button.setOnClickListener {
            goBack()
        }

        initSpinner()
    }

}