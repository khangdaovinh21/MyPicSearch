package com.example.mypicsearch

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView

// Custom ImageView that supports pinch-to-zoom and panning gestures.
class ZoomImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    private val scaleGestureDetector: ScaleGestureDetector
    private val gestureDetector: GestureDetector
    private val matrix = Matrix() // Matrix for transformation operations
    private var scaleFactor = 1.0f // Current scale factor
    private var lastFocusX = 0f // Last focus point X for scaling
    private var lastFocusY = 0f // Last focus point Y for scaling

    init {
        // Initialize ScaleGestureDetector with ScaleListener
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
        // Initialize GestureDetector with GestureListener
        gestureDetector = GestureDetector(context, GestureListener())
        // Set the initial image matrix and scale type
        imageMatrix = matrix
        scaleType = ScaleType.MATRIX
    }

    // Handle touch events for both scale and scroll gestures
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    // ScaleGestureDetector listener for pinch-to-zoom gestures
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            // Save the focus point for scaling
            lastFocusX = detector.focusX
            lastFocusY = detector.focusY
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // Calculate the new scale factor
            scaleFactor *= detector.scaleFactor
            scaleFactor = 0.1f.coerceAtLeast(scaleFactor.coerceAtMost(10.0f))

            // Calculate the translation needed to keep the focus point centered
            val focusShiftX = lastFocusX - detector.focusX
            val focusShiftY = lastFocusY - detector.focusY

            // Apply scaling and translation to the image matrix
            matrix.postScale(detector.scaleFactor, detector.scaleFactor, detector.focusX, detector.focusY)
            matrix.postTranslate(focusShiftX, focusShiftY)

            // Apply the updated matrix to the ImageView
            imageMatrix = matrix

            // Update last focus point for the next scaling operation
            lastFocusX = detector.focusX
            lastFocusY = detector.focusY

            return true
        }
    }

    // GestureDetector listener for scrolling (panning) gestures
    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            // Apply translation to the image matrix based on scroll distances
            matrix.postTranslate(-distanceX, -distanceY)
            // Apply the updated matrix to the ImageView
            imageMatrix = matrix
            return true
        }
    }
}
