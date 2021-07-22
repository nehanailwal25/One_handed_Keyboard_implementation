package com.example.keyboard_app

import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
//import androidx.test.core.app.ApplicationProvider.getApplicationContext


public class Gesture_detector : GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private val SWIPE_THRESHOLD = 100
    private val SWIPE_VELOCITY_THRESHOLD = 100
    override fun onSingleTapConfirmed(motionEvent: MotionEvent?): Boolean {
        return false
    }

    override fun onDoubleTap(motionEvent: MotionEvent?): Boolean {
        return false
    }

    override fun onDoubleTapEvent(motionEvent: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(motionEvent: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(motionEvent: MotionEvent?) {}

    override fun onSingleTapUp(motionEvent: MotionEvent?): Boolean {
        return false
    }

    override fun onScroll(motionEvent: MotionEvent?, motionEvent1: MotionEvent?, v: Float, v1: Float): Boolean {
        return false
    }

    override fun onLongPress(motionEvent: MotionEvent?) {}


//    override fun onFling(motionEvent: MotionEvent?, motionEvent1: MotionEvent?, v: Float, v1: Float): Boolean {
//        return false
//    }


    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        var result = false
        try {
//            val diffY = e2.y - e1.y
//            val diffX = e2.x - e1.x

            var diffX = e2?.x?.minus(e1!!.x) ?: 0.0F
            var diffY = e2?.y?.minus(e1!!.y) ?: 0.0F


            if (Math.abs(diffX) > Math.abs(diffY)) {
                //This is a left or right swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        this.onSwipeRight()
                    } else {
                        this.onSwipeLeft()
                    }
                    result = true
                }
            } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    this.onSwipeBottom()
                } else {
                    this.onSwipeTop()
                }
                result = true
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return result
    }


    fun onSwipeRight() {}

    fun onSwipeLeft() {}

    fun onSwipeTop() {}

    fun onSwipeBottom() {}
}