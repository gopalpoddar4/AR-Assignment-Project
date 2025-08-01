package com.gopalpoddar4.arappproject

import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.ar.core.ArCoreApk
import com.google.ar.core.ArCoreApk.Availability
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.gorisse.thomas.sceneform.scene.await


class ArActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var model: Renderable? = null
    private var infoView: ViewRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ar)

        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability != Availability.SUPPORTED_INSTALLED) {
            Toast.makeText(this, "AR not supported on this device", Toast.LENGTH_LONG).show()
            finish() // Exit activity
            return
        }

        arFragment = supportFragmentManager.findFragmentById(R.id.ar_fragment) as ArFragment

        lifecycleScope.launchWhenCreated {
            loadModels()
        }

        // On Plane Tap
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            if (model == null) {
                Toast.makeText(this, "Model loading...", Toast.LENGTH_SHORT).show()
                return@setOnTapArPlaneListener
            }
            placeModel(hitResult, plane, motionEvent)
        }

    }
    private suspend fun loadModels() {
        model = ModelRenderable.builder()
            .setSource(this, Uri.parse("models/model.glb")) // GLB file path (assets folder or raw)
            .setIsFilamentGltf(true)
            .await()


    }
    private fun placeModel(hitResult: HitResult, plane: Plane, motionEvent: MotionEvent) {
        val anchor = hitResult.createAnchor()
        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(arFragment.arSceneView.scene)

        val transformableNode = TransformableNode(arFragment.transformationSystem).apply {
            renderable = model
            setParent(anchorNode)
            renderableInstance.animate(true).start()
        }

        // Optional: Add label or UI on top
        val labelNode = Node().apply {
            setParent(transformableNode)
            localPosition = Vector3(0.0f, 1.0f, 0.0f)
            localScale = Vector3(0.5f, 0.5f, 0.5f)
            renderable = infoView
        }
    }
}