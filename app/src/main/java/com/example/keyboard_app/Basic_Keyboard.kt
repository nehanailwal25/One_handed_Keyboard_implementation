package com.example.keyboard_app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.keyboard_app.experiment_data.basic_keyboard_data
import java.io.File


class Basic_Keyboard : AppCompatActivity(){
    var text_array= arrayOf<String>("The quick brown fox jumps over the lazy dog","Here is your piece of cake you can have it","Weather is so nice and warm today",
    "Friends are meant to stay like this forever", "I just got to meet my favourite bollywood star","A bird in the hand is worth two in the bush",
    "A journey of a thousand miles begins with a single step", "A man who is his own lawyer has a fool for a client", "A place for everything and everything in its place",
    "A rose by any other name would smell as sweet")


    private var text_provided_basic: TextView ?= null
    private var text_entered_basic: EditText ?= null
    private var next: Button ?= null
    private var submit: Button ?= null

    private var isBackspaceClicked: Boolean ?= null

    //calculate error and accuracy
    private var error_rate: Int = 0
    private var accuracy_rate: Int= 0

    //for next and submit button
    private var i: Int=0

    //for words per minute
    private var total_words: Int=0
    private var word_count: Float=0f

    //For error percentage
    var text_len: Int =0


//    //Timer
//    var START_TIME_IN_MILLIS = 600000L
//    lateinit var countdown_timer: CountDownTimer
//    var timer_running: Boolean = false;
//    var time_left_in_millis = START_TIME_IN_MILLIS

    //Timer
    var start_time: Long ?= 0L
    var end_time: Long ?= 0L
    var time_taken: Long ?= 0L

    var previousTextLength:String ?= null

    var text_user: String ?= null

    //Data of the experiment
    val keyboard_data = mutableListOf<basic_keyboard_data>()

    //next keyboard
    var move: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic__keyboard)

        text_provided_basic = findViewById(R.id.text_provided_basic)
        text_entered_basic= findViewById(R.id.text_entered_basic)

        next= findViewById(R.id.next_basic)
//        submit= findViewById(R.id.Submit)

        move = findViewById(R.id.Move)

        //On ressing next button
        next?.setOnClickListener{

            end_time= System.currentTimeMillis()
            time_taken= end_time!! - start_time!!

            total_words= text_entered_basic?.length()!!

            word_count= ((total_words*60000)/(time_taken!! *5)).toFloat()


            text_len= text_provided_basic!!.length()

//            Log.i("Total words:",total_words.toString())
//            Log.i("Word Count:",word_count.toString())
//            Log.i("Time taken:", time_taken.toString())
//            Log.i("error percent:", error_percent.toString())
//            Log.i("prov len:", text_len.toString())
//            Log.i("Error percent:", ((error_rate.toFloat()*100)/text_len.toFloat()).toString())

            //Saving data
            val data_collected = basic_keyboard_data(Entry_keypad= "Base Line Keyboard",
                    provided_text= text_provided_basic!!.text.toString(),
                    entered_text = text_entered_basic!!.text.toString(),
                    provided_text_length = text_provided_basic!!.length(),
                    entered_text_length = text_entered_basic!!.length(),
                    error_rate = error_rate,
                    accuracy_rate = accuracy_rate,
                    time_taken = time_taken!!,
                    error_percent = (error_rate.toFloat()*100)/text_len.toFloat(),
                    words_per_minute = word_count)

            keyboard_data.add(data_collected)

//            Log.i("Provided Text:",text_provided_basic!!.text.toString())
//            Log.i("Entered Text:",text_entered_basic!!.text.toString())
//            Log.i("Provided text length:",text_provided_basic!!.length().toString())
//            Log.i("Entered text length:",text_entered_basic!!.length().toString())
//            Log.i("Error rate:",error_rate.toString())
//            Log.i("Accuracy Rate:",accuracy_rate.toString())
//            Log.i("Time taken:",time_taken.toString())



            text_entered_basic?.text?.clear()
            //text_entered_basic?.setText("")

            changeText()

            error_rate=0
            accuracy_rate=0
            i += 1

//            time_taken= 600000L - time_left_in_millis

//            stopTimer()

//            Log.i("Time taken:",time_left_in_millis.toString())

//            Log.i("Time calculated", time_taken.toString())
//            Log.i("End time:", start_time.toString())

//            resetTimer()

            if(i==9){
                next?.text= "Submit"

            }

            if (i==10){

                val data = StringBuilder()
                data.append("Entry Keyboard, Provided Text, Entered Text, Provided Text Length, Entered Text Length, Error Rate, Accuracy Rate, Time Taken, Error Percent, Words per minute")
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

                move?.visibility= View.VISIBLE
                text_provided_basic?.visibility= View.INVISIBLE
                text_entered_basic?.visibility= View.INVISIBLE

            }
        }

        move?.setOnClickListener{
            var intent_move = Intent(this,Built_Keyboard::class.java)
            startActivity(intent_move)
        }


        text_entered_basic?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                text_user= s.toString()
                if(s.toString().length==1) {
                    start_time= System.currentTimeMillis()
                    Log.i("Start time:", start_time.toString())
                }
//                startTimer()
                if(!(isBackspaceClicked!!)){
                    accuracy_rate += 1
                    Log.i("Accuracy Rate:", accuracy_rate.toString())
                }else{
                    error_rate += 1
                    accuracy_rate -= 2
                    Log.i("error rate:", error_rate.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isBackspaceClicked = after<count
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(text_entered_basic.toString().isNotEmpty()){
                }
            }


        })
    }

//    private fun createCSV() {
//
//    }

    private fun changeText() {
        when (text_provided_basic!!.text) {
            text_array[0] -> {
                text_provided_basic!!.text=text_array[1]
            }
            text_array[1] -> {
                text_provided_basic!!.text=text_array[2]
            }
            text_array[2] -> {
                text_provided_basic!!.text=text_array[3]
            }
            text_array[3] -> {
                text_provided_basic!!.text=text_array[4]
            }
            text_array[4] -> {
                text_provided_basic!!.text=text_array[5]
            }
            text_array[5] -> {
                text_provided_basic!!.text=text_array[6]
            }
            text_array[6] -> {
                text_provided_basic!!.text=text_array[7]
            }
            text_array[7] -> {
                text_provided_basic!!.text=text_array[8]
            }
            text_array[8] -> {
                text_provided_basic!!.text=text_array[9]
            }
        }
    }
}

