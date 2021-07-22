package com.example.keyboard_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView

class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)

        var start_keyboard1= findViewById<Button>(R.id.start_keyboard1)
        var start_keyboard2 = findViewById<Button>(R.id.start_keyboard2)

        var instruction= findViewById<TextView>(R.id.instruction)

        instruction.setText(Html.fromHtml(getString(R.string.instruction_text)))
        instruction.movementMethod= ScrollingMovementMethod()

        start_keyboard2.setOnClickListener{
            var intent2= Intent(this,Built_Keyboard::class.java)
            //Log.i("yoo:","yoo")
//            print("yoo")
            startActivity(intent2)
        }

        start_keyboard1.setOnClickListener{
            var intent3 = Intent(this, Basic_Keyboard::class.java)
            startActivity(intent3)
        }

    }
}