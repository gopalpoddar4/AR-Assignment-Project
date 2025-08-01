package com.gopalpoddar4.arappproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class DetailsActivity : AppCompatActivity() {
    private lateinit var drillData: RCVModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra("id",1)

        val back = findViewById<ImageView>(R.id.back)
        val image = findViewById<ImageView>(R.id.imageView)
        val discription = findViewById<TextView>(R.id.discription)
        val drill = findViewById<TextView>(R.id.drill)
        val tip = findViewById<TextView>(R.id.tip)
        val startArDrill = findViewById<MaterialButton>(R.id.startArDrillButton)

        back.setOnClickListener {
            finish()
        }

        drillData = DrillDataProvider.getDrillById(id)!!

        drill.text = drillData.drill
        image.setImageResource(drillData.image)
        discription.text = drillData.discription
        tip.text = drillData.tip

        startArDrill.setOnClickListener {
            startActivity(Intent(this, ArActivity::class.java))
        }
    }
}