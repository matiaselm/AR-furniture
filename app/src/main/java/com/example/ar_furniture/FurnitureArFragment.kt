package com.example.ar_furniture

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment

class FurnitureArFragment : ArFragment() {
    lateinit var furniture: Furniture
    private var testRenderable: ModelRenderable? = null
    private lateinit var currentContext: Context

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
        currentContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        furniture = arguments!!.getSerializable(MODEL) as Furniture

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

        renderableFuture.thenAccept{it -> testRenderable = it}

        renderableFuture.exceptionally { throwable ->
            Toast.makeText(currentContext, "Unable to create renderable", Toast.LENGTH_SHORT).show()
            null
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
