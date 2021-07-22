package com.journaldev.keyboard_app

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

//import android.R
//import android.content.Context
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import android.view.GestureDetector
//import android.view.GestureDetector.SimpleOnGestureListener
//import android.view.MotionEvent
//import android.view.View
//import android.view.View.OnTouchListener
//import android.widget.Button
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val button = findViewById<Button>(R.id.button)
//        button.setOnTouchListener(object : OnSwipeTouchListener(this) {
//            override fun onSwipeTop() {
//                Toast.makeText(applicationContext, "Swiped top", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSwipeRight() {
//                Toast.makeText(applicationContext, "Swiped right", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSwipeLeft() {
//                Toast.makeText(applicationContext, "Swiped left", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSwipeBottom() {
//                Toast.makeText(applicationContext, "Swiped bottom", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    internal class OnSwipeTouchListener(ctx: Context?) : View.OnTouchListener {
        private val gestureDetector: GestureDetector
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
            private val SWIPE_THRESHOLD = 100
            private val SWIPE_VELOCITY_THRESHOLD = 100
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
//                Log.i("TAG", "onSingleTapConfirmed:")
//                Toast.makeText(applicationContext, "Single Tap Detected", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onLongPress(e: MotionEvent) {
//                Log.i("TAG", "onLongPress:")
//                Toast.makeText(applicationContext, "Long Press Detected", Toast.LENGTH_SHORT).show()
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                //Toast.makeText(applicationContext, "Double Tap Detected", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                var result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom()
                        } else {
                            onSwipeTop()
                        }
                        result = true
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                return result
            }

//            companion object {
//                private const val SWIPE_THRESHOLD = 300
//                private const val SWIPE_VELOCITY_THRESHOLD = 300
//            }
        }

        fun onSwipeRight() {}
        fun onSwipeLeft() {}
        fun onSwipeTop() {}
        fun onSwipeBottom() {}

        init {
            gestureDetector = GestureDetector(ctx, GestureListener())
        }
    }