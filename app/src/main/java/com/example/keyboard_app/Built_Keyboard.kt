package com.example.keyboard_app

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.GestureDetectorCompat
import com.example.keyboard_app.experiment_data.built_keyboard_data
import java.io.File
import kotlin.math.abs


class Built_Keyboard : AppCompatActivity() {

    //Text Area values
    var text_array= arrayOf<String>("The quick brown fox jumps over the lazy dog","Here is your piece of cake you can have it","Weather is so nice and warm today",
            "Friends are meant to stay like this forever", "I just got to meet my favourite bollywood star","A bird in the hand is worth two in the bush",
            "A journey of a thousand miles begins with a single step", "A man who is his own lawyer has a fool for a client", "A place for everything and everything in its place",
            "A rose by any other name would smell as sweet")

    //Gesture detection
    private lateinit var gestureDetector: GestureDetectorCompat

    var x2:Float = 0.0f
    var x1:Float = 0.0f
    var y2:Float = 0.0f
    var y1:Float = 0.0f

    companion object{
        const val MIN_DISTANCE = 150
    }

    //all alphabets
    private var alpha1: Button? = null
    private var alpha2: Button? = null
    private var alpha3: Button? = null
    private var alpha4: Button? = null
    private var alpha5: Button? = null
    private var alpha6: Button? = null
    private var alpha7: Button? = null
    private var alpha8: Button? = null
    private var alpha9: Button? = null
    private var alpha10: Button? = null
    private var alpha11: Button? = null
    private var alpha12: Button? = null
    private var alpha13: Button? = null
    private var alpha14: Button? = null
    private var alpha15: Button? = null
    private var alpha16: Button? = null
    private var alpha17: Button? = null
    private var alpha18: Button? = null
    private var alpha19: Button? = null
    private var alpha20: Button? = null
    private var alpha21: Button? = null
    private var alpha22: Button? = null
    private var alpha23: Button? = null
    private var alpha24: Button? = null
    private var alpha25: Button? = null
    private var alpha26: Button? = null

    //Given text
    private var text_provided_built: TextView? = null
    private var next: Button ?= null

    //Caps lock key
    private var caps_key: Button? = null

    //Numbers key
    private var num_key: Button ?= null

    //Enter key
    private var enter_key: Button ?= null

    //Backspace key
    private var backspace_key: Button ?= null
    private var c: Char ?= null // to store the last character
    private var input: String ?=null // String entered by the user

    //Timer start key
    private var start_timer: Button ?= null

    //Space key
    private var space_key: Button ?= null

    //TextView for text entry
    private var text_entered_built: TextView ?= null

    //calculate error and accuracy
    private var error_rate: Int = 0
    private var accuracy_rate: Int= 0

    //Flag for caps Capital
    var caps_flag=0

    //Flag for numbers
    var num_flag=0     //currently alpha

    //Flag for num_alpha_key
    var num_alpha_flag=0 // in alpha state

    //Flag for Special chars
    var special_chars_flag=0 // caps up sign is there

    //Timer
    var start_time: Long ?= 0L
    var end_time: Long ?= 0L
    var time_taken: Long ?= 0L

    //Data of the experiment
    val keyboard_data = mutableListOf<built_keyboard_data>()

    //Big buttons Layout
    private var layout1: LinearLayout ?= null
    private var layout2: LinearLayout ?= null
    private var layout3: LinearLayout ?= null
    private var layout4: LinearLayout ?= null
    private var layout5: LinearLayout ?= null
    private var layout6: LinearLayout ?= null
    private var layout7: LinearLayout ?= null
    private var layout8: LinearLayout ?= null
    private var layout9: LinearLayout ?= null
    private var layout10: LinearLayout ?= null
    private var layout11: LinearLayout ?= null
    private var layout12: LinearLayout ?= null
    private var layout13: LinearLayout ?= null

    //for next and submit button
    private var i: Int=0

    //for words per minute
    private var total_words: Int=0
    private var word_count: Float=0f

    //For error percentage
    var text_len: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_built__keyboard)
        gestureDetector= GestureDetectorCompat(this, Gesture_detector())

        gestureDetector.setIsLongpressEnabled(false)

        //Focus in the text entered by user
        text_entered_built?.isFocusableInTouchMode = true



        //Alphabet keys assigned
        alpha1= findViewById<Button>(R.id.alpha1)
        alpha2= findViewById<Button>(R.id.alpha2)
        alpha3= findViewById<Button>(R.id.alpha3)
        alpha4= findViewById<Button>(R.id.alpha4)
        alpha5= findViewById<Button>(R.id.alpha5)
        alpha6= findViewById<Button>(R.id.alpha6)
        alpha7= findViewById<Button>(R.id.alpha7)
        alpha8= findViewById<Button>(R.id.alpha8)
        alpha9= findViewById<Button>(R.id.alpha9)
        alpha10= findViewById<Button>(R.id.alpha10)
        alpha11= findViewById<Button>(R.id.alpha11)
        alpha12= findViewById<Button>(R.id.alpha12)
        alpha13= findViewById<Button>(R.id.alpha13)
        alpha14= findViewById<Button>(R.id.alpha14)
        alpha15= findViewById<Button>(R.id.alpha15)
        alpha16= findViewById<Button>(R.id.alpha16)
        alpha17= findViewById<Button>(R.id.alpha17)
        alpha18= findViewById<Button>(R.id.alpha18)
        alpha19= findViewById<Button>(R.id.alpha19)
        alpha20= findViewById<Button>(R.id.alpha20)
        alpha21= findViewById<Button>(R.id.alpha21)
        alpha22= findViewById<Button>(R.id.alpha22)
        alpha23= findViewById<Button>(R.id.alpha23)
        alpha24= findViewById<Button>(R.id.alpha24)
        alpha25= findViewById<Button>(R.id.alpha25)
        alpha26= findViewById<Button>(R.id.alpha26)

        //Given text
        text_provided_built= findViewById<TextView>(R.id.text_provided_built)
        next= findViewById(R.id.next_built)

        //Caps lock key
        caps_key = findViewById<Button>(R.id.caps)

        //Numbers key
        num_key= findViewById<Button>(R.id.num)

        //Enter key
        enter_key= findViewById<Button>(R.id.enter)

        //Backspace key
        backspace_key= findViewById<Button>(R.id.backspace)

        //Space key
        space_key= findViewById<Button>(R.id.space)

        //timer start key
        start_timer= findViewById(R.id.start_timer)

        // Text entry by user
        text_entered_built = findViewById<TextView>(R.id.text_entered_built)

        //Big button Layout
        layout1= findViewById<LinearLayout>(R.id.layout1)
        layout2= findViewById<LinearLayout>(R.id.layout2)
        layout3= findViewById<LinearLayout>(R.id.layout3)
        layout4= findViewById<LinearLayout>(R.id.layout4)
        layout5= findViewById<LinearLayout>(R.id.layout5)
        layout6= findViewById<LinearLayout>(R.id.layout6)
        layout7= findViewById<LinearLayout>(R.id.layout7)
        layout8= findViewById<LinearLayout>(R.id.layout8)
        layout9= findViewById<LinearLayout>(R.id.layout9)
        layout10= findViewById<LinearLayout>(R.id.layout10)
        layout11= findViewById<LinearLayout>(R.id.layout11)
        layout12= findViewById<LinearLayout>(R.id.layout12)
        layout13= findViewById<LinearLayout>(R.id.layout13)

        // Turning all keys to caps and non caps
        caps_key!!.setOnClickListener(){
            text_entered_built?.requestFocus()
//            if (special_chars_flag==0){
                caps_all()
//                //special_chars_flag=1
//            }
//            else if (special_chars_flag==1){
//                more_special_chars()
//                special_chars_flag=2
//            }
//            else if (special_chars_flag==2){
//                change_to_nums()
//                special_chars_flag=0
//            }

        }

        //Timers start on button click
        start_timer?.setOnClickListener{
            start_time= System.currentTimeMillis()
            Log.i("Start time:", start_time.toString())
        }

        //Changing the text in TextArea
        next?.setOnClickListener{
//            Log.i("error rate:", error_rate.toString())
//            Log.i("Accuracy rate:", accuracy_rate.toString())
//            Log.i("Start time:", start_time.toString())

            end_time= System.currentTimeMillis()
            time_taken= end_time!! - start_time!!

            total_words= text_entered_built?.length()!!

            word_count= ((total_words*60000)/(time_taken!! *5)).toFloat()

            text_len= text_provided_built!!.length()

            Log.i("taken time:", time_taken.toString())
            Log.i("prov len:", text_len.toString())

            //Saving data
            val data_collected = built_keyboard_data(Entry_keypad = "Implemented Keyboard",
                    provided_text= text_provided_built!!.text.toString(),
                    entered_text = text_entered_built!!.text.toString(),
                    provided_text_length = text_provided_built!!.length(),
                    entered_text_length = text_entered_built!!.length(),
                    error_rate = error_rate,
                    accuracy_rate = accuracy_rate,
                    time_taken = time_taken!!,
                    error_percent = (error_rate.toFloat()*100)/text_len.toFloat(),
                    words_per_minute = word_count)

            keyboard_data.add(data_collected)

//            Log.i("Provided Text:",text_provided_built!!.text.toString())
//            Log.i("Entered Text:",text_entered_built!!.text.toString())
//            Log.i("Provided text length:",text_provided_built!!.length().toString())
//            Log.i("Entered text length:",text_entered_built!!.length().toString())
//            Log.i("Error rate:",error_rate.toString())
//            Log.i("Accuracy Rate:",accuracy_rate.toString())
//            Log.i("Time taken:",time_taken.toString())

            text_entered_built?.text = ""

            changeText()

            error_rate=0
            accuracy_rate=0
            i += 1

            if(i==9){
                next?.text= "Submit"
            }

            if (i==10){

                val data = StringBuilder()
                data.append("Entry Keypad, Provided Text, Entered Text, Provided Text Length, Entered Text Length, Error Rate, Accuracy Rate, Time Taken, Error Percent, Words per minute")
                data.append("\n")

                keyboard_data.forEach {data_collected ->
                    data.append(data_collected.Entry_keypad)
                    data.append(',')
                    data.append(data_collected.provided_text)
                    data.append(',')
                    data.append(data_collected.entered_text)
                    data.append(',')
                    data.append(data_collected.provided_text_length)
                    data.append(',')
                    data.append(data_collected.entered_text_length)
                    data.append(',')
                    data.append(data_collected.error_rate)
                    data.append(',')
                    data.append(data_collected.accuracy_rate)
                    data.append(',')
                    data.append(data_collected.time_taken)
                    data.append(',')
                    data.append(data_collected.error_percent)
                    data.append(',')
                    data.append(data_collected.words_per_minute)
                    data.append('\n')
                }

                //var fileWriter: FileWriter? = null
                try{
                    val out: java.io.FileOutputStream = openFileOutput("data.csv", Context.MODE_PRIVATE)
                    out.write((data.toString()).toByteArray())
                    out.close()

                    val context = applicationContext
                    val filelocation = File(filesDir, "data.csv")
                    val path: Uri = FileProvider.getUriForFile(context, "com.example.keyboard_app.fileprovider", filelocation)
                    val fileIntent = Intent(Intent.ACTION_SEND)
                    fileIntent.setType("text/csv")
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data")
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path)
                    startActivity(Intent.createChooser(fileIntent, "Send mail"))
                }
                catch(e: Exception){
                    println("Writing CSV error!")
                    e.printStackTrace()
                }

            }

        }

        //Typing alphas into text area
        type_alpha()

        //For double click and single click in the layout will be for swipe as well
        layout_click()

        //For Numbers and special Characters
        num_key!!.setOnClickListener(){
            if (num_alpha_flag==0){
                change_to_nums()
                num_alpha_flag=1
            }
            else if(num_alpha_flag==1){
                change_to_alpha()
                num_alpha_flag=0
            }
            num_flag=1
        }


    }

    private fun changeText() {
        when (text_provided_built!!.text) {
            text_array[0] -> {
                text_provided_built!!.text=text_array[1]
            }
            text_array[1] -> {
                text_provided_built!!.text=text_array[2]
            }
            text_array[2] -> {
                text_provided_built!!.text=text_array[3]
            }
            text_array[3] -> {
                text_provided_built!!.text=text_array[4]
            }
            text_array[4] -> {
                text_provided_built!!.text=text_array[5]
            }
            text_array[5] -> {
                text_provided_built!!.text=text_array[6]
            }
            text_array[6] -> {
                text_provided_built!!.text=text_array[7]
            }
            text_array[7] -> {
                text_provided_built!!.text=text_array[8]
            }
            text_array[8] -> {
                text_provided_built!!.text=text_array[9]
            }
        }
    }

//    private fun more_special_chars() {
//        caps_key?.setBackgroundResource(R.drawable.ic_back_to_nums)
//        alpha1!!.text= '~'.toString()
//        alpha2!!.text= '`'.toString()
//        alpha3!!.text= '.'.toString()
//        alpha4!!.text= '*'.toString()
//        alpha5!!.text= '['.toString()
//        alpha6!!.text= ']'.toString()
//        alpha7!!.text= '$'.toString()
//        alpha8!!.text= '%'.toString()
//        alpha9!!.text= '<'.toString()
//        alpha10!!.text= '>'.toString()
//        alpha11!!.text= '©'.toString()
//        alpha12!!.text= '®'.toString()
//        alpha13!!.text= '™'.toString()
//        alpha14!!.text= '÷'.toString()
//        alpha15!!.text= '°'.toString()
//        alpha16!!.text= '•'.toString()
//        alpha17!!.text= '?'.toString()
//        alpha18!!.text= '¿'.toString()
//        alpha19!!.text= '¢'.toString()
//        alpha20!!.text= '£'.toString()
//        alpha21!!.text= '¥'.toString()
//        alpha22!!.text= '₧'.toString()
//        alpha23!!.text= '½'.toString()
//        alpha24!!.text= '¼'.toString()
//        alpha25!!.text= '«'.toString()
//        alpha26!!.text= '»'.toString()
//
//        special_chars_flag=2
//
//    }

    private fun change_to_alpha() {
        //caps_key?.setBackgroundResource(R.drawable.ic_caps_up)
        num_key?.setBackgroundResource(R.drawable.ic_numbers)
        alpha1!!.text= 'e'.toString()
        alpha2!!.text= 'm'.toString()
        alpha3!!.text= 't'.toString()
        alpha4!!.text= 'f'.toString()
        alpha5!!.text= 'a'.toString()
        alpha6!!.text= 'w'.toString()
        alpha7!!.text= 'i'.toString()
        alpha8!!.text= 'y'.toString()
        alpha9!!.text= 'n'.toString()
        alpha10!!.text= 'o'.toString()
        alpha11!!.text= 'c'.toString()
        alpha12!!.text= 'u'.toString()
        alpha13!!.text= 'v'.toString()
        alpha14!!.text= 'z'.toString()
        alpha15!!.text= 'k'.toString()
        alpha16!!.text= 'x'.toString()
        alpha17!!.text= 'q'.toString()
        alpha18!!.text= 'j'.toString()
        alpha19!!.text= 's'.toString()
        alpha20!!.text= 'h'.toString()
        alpha21!!.text= 'b'.toString()
        alpha22!!.text= 'l'.toString()
        alpha23!!.text= 'p'.toString()
        alpha24!!.text= 'd'.toString()
        alpha25!!.text= 'g'.toString()
        alpha26!!.text= 'r'.toString()

        num_alpha_flag=0
    }

    private fun change_to_nums() {
        //num_key?.background= Drawable.createFromPath("@drawable/ic_alpha")
        //caps_key?.setBackgroundResource(R.drawable.ic_more_special_chars)
        num_key?.setBackgroundResource(R.drawable.ic_alpha)

        alpha1!!.text= 0.toString()
        alpha2!!.text= 1.toString()
        alpha3!!.text= 2.toString()
        alpha4!!.text= 3.toString()
        alpha5!!.text= 4.toString()
        alpha6!!.text= 5.toString()
        alpha7!!.text= 6.toString()
        alpha8!!.text= 7.toString()
        alpha9!!.text= 8.toString()
        alpha10!!.text= 9.toString()
        alpha11!!.text= '@'.toString()
        alpha12!!.text= '#'.toString()
        alpha13!!.text= '_'.toString()
        alpha14!!.text= '&'.toString()
        alpha15!!.text= '-'.toString()
        alpha16!!.text= '+'.toString()
        alpha17!!.text= '('.toString()
        alpha18!!.text= ')'.toString()
        alpha19!!.text= '/'.toString()
        alpha20!!.text= '*'.toString()
        alpha21!!.text= '"'.toString()
        alpha22!!.text= '"'.toString()
        alpha23!!.text= ':'.toString()
        alpha24!!.text= ';'.toString()
        alpha25!!.text= '!'.toString()
        alpha26!!.text= '?'.toString()

        num_alpha_flag=1
        special_chars_flag=1

//            if(num_flag==0)
//            {
//                alpha1!!.text= 0.toString()
//                alpha2!!.text= 1.toString()
//
//                num_flag=1
//            }
//            else if(num_flag==1)
//            {
//                alpha1!!.text= 'e'.toString()
//                alpha2!!.text= 'm'.toString()
//
//                caps_flag=0
//                num_flag=0
//                //caps_all()
//            }
    }


    private fun caps_all() {
        if(num_alpha_flag==0){
            if(caps_flag==0)
            {
                alpha1!!.isAllCaps=false
                alpha2!!.isAllCaps=false
                alpha3!!.isAllCaps=false
                alpha4!!.isAllCaps=false
                alpha5!!.isAllCaps=false
                alpha6!!.isAllCaps=false
                alpha7!!.isAllCaps=false
                alpha8!!.isAllCaps=false
                alpha9!!.isAllCaps=false
                alpha10!!.isAllCaps=false
                alpha11!!.isAllCaps=false
                alpha12!!.isAllCaps=false
                alpha13!!.isAllCaps=false
                alpha14!!.isAllCaps=false
                alpha15!!.isAllCaps=false
                alpha16!!.isAllCaps=false
                alpha17!!.isAllCaps=false
                alpha18!!.isAllCaps=false
                alpha19!!.isAllCaps=false
                alpha20!!.isAllCaps=false
                alpha21!!.isAllCaps=false
                alpha22!!.isAllCaps=false
                alpha23!!.isAllCaps=false
                alpha24!!.isAllCaps=false
                alpha25!!.isAllCaps=false
                alpha26!!.isAllCaps=false

                caps_flag=1  //flag value for non caps alphabets
            }
            else if (caps_flag==1)
            {
                alpha1!!.isAllCaps=true
                alpha2!!.isAllCaps=true
                alpha3!!.isAllCaps=true
                alpha4!!.isAllCaps=true
                alpha5!!.isAllCaps=true
                alpha6!!.isAllCaps=true
                alpha7!!.isAllCaps=true
                alpha8!!.isAllCaps=true
                alpha9!!.isAllCaps=true
                alpha10!!.isAllCaps=true
                alpha11!!.isAllCaps=true
                alpha12!!.isAllCaps=true
                alpha13!!.isAllCaps=true
                alpha14!!.isAllCaps=true
                alpha15!!.isAllCaps=true
                alpha16!!.isAllCaps=true
                alpha17!!.isAllCaps=true
                alpha18!!.isAllCaps=true
                alpha19!!.isAllCaps=true
                alpha20!!.isAllCaps=true
                alpha21!!.isAllCaps=true
                alpha22!!.isAllCaps=true
                alpha23!!.isAllCaps=true
                alpha24!!.isAllCaps=true
                alpha25!!.isAllCaps=true
                alpha26!!.isAllCaps=true

                caps_flag=0
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return  if (gestureDetector.onTouchEvent(event)){
            true
        }else{
            super.onTouchEvent(event)
        }

    }


    private fun type_alpha()
    {
        alpha1?.setOnClickListener{
            accuracy_rate += 1
            Log.i("accuracy rate:", accuracy_rate.toString())
            if(caps_flag==0){
                text_entered_built!!.append(alpha1!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha1!!.text.toString())
            }
        }


        alpha2?.setOnClickListener{
            accuracy_rate += 1
            Log.i("accuracy rate:", accuracy_rate.toString())
            if(caps_flag==0){
                text_entered_built!!.append(alpha2!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha2!!.text.toString())
            }
        }

        alpha3?.setOnClickListener {
            accuracy_rate += 1
            if (caps_flag == 0) {
                text_entered_built!!.append(alpha3!!.text.toString().toUpperCase())
            } else {
                text_entered_built!!.append(alpha3!!.text.toString())
            }
        }

        alpha4?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha4!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha4!!.text.toString())
            }
        }

        alpha5?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha5!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha5!!.text.toString())
            }
        }

        alpha6?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha6!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha6!!.text.toString())
            }
        }

        alpha7?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha7!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha7!!.text.toString())
            }
        }

        alpha8?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha8!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha8!!.text.toString())
            }
        }

        alpha9?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha9!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha9!!.text.toString())
            }
        }

        alpha10?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha10!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha10!!.text.toString())
            }
        }

        alpha11?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha11!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha11!!.text.toString())
            }
        }

        alpha12?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha12!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha12!!.text.toString())
            }
        }

        alpha13?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha13!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha13!!.text.toString())
            }
        }

        alpha14?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha14!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha14!!.text.toString())
            }
        }

        alpha15?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha15!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha15!!.text.toString())
            }
        }

        alpha16?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha16!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha16!!.text.toString())
            }
        }

        alpha17?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha17!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha17!!.text.toString())
            }
        }

        alpha18?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha18!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha18!!.text.toString())
            }
        }

        alpha19?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha19!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha19!!.text.toString())
            }
        }

        alpha20?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha20!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha20!!.text.toString())
            }
        }

        alpha21?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha21!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha21!!.text.toString())
            }
        }

        alpha22?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha22!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha22!!.text.toString())
            }
        }

        alpha23?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha23!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha23!!.text.toString())
            }
        }

        alpha24?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha24!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha24!!.text.toString())
            }
        }

        alpha25?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha25!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha25!!.text.toString())
            }
        }

        alpha26?.setOnClickListener{
            accuracy_rate += 1
            if(caps_flag==0){
                text_entered_built!!.append(alpha26!!.text.toString().toUpperCase())
            }
            else{
                text_entered_built!!.append(alpha26!!.text.toString())
            }
        }

        space_key?.setOnClickListener{
            text_entered_built!!.append(" ")
            accuracy_rate += 1
        }

        backspace_key?.setOnClickListener{
//            input= text_entered_built.toString()
//            input= input!!.substring(0, input!!.length-1)
//            text_entered_built!!.text=input
            text_entered_built!!.text= text_entered_built!!.text.toString().substring(0,text_entered_built!!.text.length-1)
            error_rate += 1
            accuracy_rate -= 2
//            Log.i("error rate:", error_rate.toString())
//            Log.i("Accuracy rate:", accuracy_rate.toString())
        }

        enter_key?.setOnClickListener{
            text_entered_built?.append("\n")
        }
    }


    //Single and double click on layout
    private fun layout_click() {

        layout1?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if(caps_flag==0){
                        text_entered_built!!.append(alpha1!!.text.toString().toUpperCase())
                    }
                    else{
                        text_entered_built!!.append(alpha1!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if(caps_flag==0){
                        text_entered_built!!.append(alpha2!!.text.toString().toUpperCase())
                    }
                    else{
                        text_entered_built!!.append(alpha2!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }


//                fun onSwipeTop() {
//                    this.onSwipeTop()
//                    if(caps_flag==0){
//                        text_entered_built!!.append(alpha1!!.text.toString().toUpperCase())
//                    }
//                    else{
//                        text_entered_built!!.append(alpha1!!.text.toString())
//                    }
//                    this.onSwipeTop()
//                }


//                fun onSwipeBottom() {
//                    this.onSwipeBottom()
//                    if(caps_flag==0){
//                        text_entered_built!!.append(alpha2!!.text.toString().toUpperCase())
//                    }
//                    else{
//                        text_entered_built!!.append(alpha2!!.text.toString())
//                    }
//                    this.onSwipeBottom()
//                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout2?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha3!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha3!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha4!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha4!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout3?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha5!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha5!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha6!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha6!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout4?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha7!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha7!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha8!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha8!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout5?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha9!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha9!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha10!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha10!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout6?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha11!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha11!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha12!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha12!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout7?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha13!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha13!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha14!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha14!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout8?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha15!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha15!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha16!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha16!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout9?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha17!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha17!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha18!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha18!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout10?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha19!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha19!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha20!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha20!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout11?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha21!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha21!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha22!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha22!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout12?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha23!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha23!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha24!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha24!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

        layout13?.setOnTouchListener(object : OnTouchListener {
            var gestureDetector = GestureDetector(applicationContext, object : SimpleOnGestureListener() {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha25!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha25!!.text.toString())
                    }
                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    accuracy_rate += 1
                    if (caps_flag == 0) {
                        text_entered_built!!.append(alpha26!!.text.toString().toUpperCase())
                    } else {
                        text_entered_built!!.append(alpha26!!.text.toString())
                    }
                    return super.onDoubleTap(e)
                }
            })

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(motionEvent)
                return true
            }
        })

    }
}