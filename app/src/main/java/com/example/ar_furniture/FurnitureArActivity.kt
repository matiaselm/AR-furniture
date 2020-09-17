package com.example.ar_furniture

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
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


class FurnitureArActivity: AppCompatActivity() {
    lateinit var furniture: Furniture
    private lateinit var arFragment: ArFragment
    private var testRenderable: ModelRenderable? = null
    private var currentContext: Context = this

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
            Toast.makeText(currentContext, "Ouch!!!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_ar)

        val b = this.intent.extras
        if (b != null) {
            furniture = b.getSerializable("furn") as Furniture
        }

        arFragment = supportFragmentManager.findFragmentById(R.id.furniture_fragment) as ArFragment

        // furniture.src on tiedostonimi
        val modelUri = Uri.parse(furniture.src)

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
            .setRegistryId(furniture.name)
            .build()

        renderableFuture.thenAccept { it -> testRenderable = it }

        renderableFuture.exceptionally { throwable ->
            Toast.makeText(currentContext, "Unable to create renderable", Toast.LENGTH_SHORT).show()
            null
        }

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            addObj(hitResult, plane, motionEvent)
        }
    }

}