package com.example.keyboard_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var main_text= findViewById<TextView>(R.id.main_text)

        var start = findViewById<Button>(R.id.start)

        main_text.setText(Html.fromHtml(getString(R.string.welcome_test)))
        main_text.movementMethod= ScrollingMovementMethod()

        start.setOnClickListener{
            var intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent)
        }

    }
}