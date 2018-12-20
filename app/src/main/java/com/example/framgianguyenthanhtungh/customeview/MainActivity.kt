package com.example.framgianguyenthanhtungh.customeview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var customDiagram : CustomDiagram

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customDiagram = findViewById(R.id.custom)
        custom.setOnClickListener {
            customDiagram.changeColor()
        }
    }
}
