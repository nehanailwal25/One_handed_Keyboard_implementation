import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener


class OnSwipeTouchListener : OnTouchListener {
    protected var gestureDetector: GestureDetector? = null
    val IDLE = 0
    val TOUCH = 1
    val PINCH = 2
    var touchState = 0
    var dist0 = 0.0
    var distCurrent = 0.0
    private var CONTEXT: Context? = null

    constructor(ctx: Context?) {
        gestureDetector = GestureDetector(ctx, GestureListener())
        CONTEXT = ctx
    }

    constructor() {}

    fun onSwipeRight() {}
    fun onSwipeLeft() {}
    fun onSwipeTop(diffY: Float) {}
    fun onSwipeBottom(diffY: Float) {}
    fun onDoubleTap() {}

    //    public abstract void onSwipeRight();
    //
    //    public abstract void onSwipeLeft() ;
    //
    //    public abstract void onSwipeTop(double diff) ;
    //
    //    public abstract void onSwipeBottom(double diff);
    //
    //    public abstract void onPinch(double distCurrent) ;
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector!!.onTouchEvent(event)
    }

    private inner class GestureListener : SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(
                            velocityX
                        ) > SWIPE_VELOCITY_THRESHOLD
                    ) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                    }
                    result = true
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(
                        velocityY
                    ) > SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffY > 0) {
                        onSwipeBottom(diffY)
                    } else {
                        onSwipeTop(diffY)
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            this@OnSwipeTouchListener.onDoubleTap()
            return super.onDoubleTap(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return super.onSingleTapConfirmed(e)
        }
    }

    companion object {
        const val SWIPE_THRESHOLD = 50
        const val SWIPE_VELOCITY_THRESHOLD = 50
    }
}