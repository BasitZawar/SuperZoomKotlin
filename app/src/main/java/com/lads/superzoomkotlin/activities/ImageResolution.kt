package com.lads.superzoomkotlin.activities

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jsibbold.zoomage.ZoomageView
import com.lads.superzoomkotlin.EditImageActivity
import com.lads.superzoomkotlin.R
import java.io.IOException
import kotlin.math.max
import kotlin.math.min


class ImageResolution : AppCompatActivity(), View.OnClickListener {
    private var bitmap: Bitmap? = null
    private lateinit var imgResolution: ZoomageView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    private var imgArrowBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_resolution)

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())


        imgResolution = findViewById(R.id.imgResolution)
        imgArrowBack = findViewById(R.id.imgArrowBack)

        imgArrowBack!!.setOnClickListener {
            finish()
        }
//        Glide.with(this).load(R.drawable.ic_launcher_background).into(imgResolution)


        var uri = intent.getStringExtra("pickedImage")
        bitmap = intent.getParcelableExtra<Bitmap>("capturedImage")

        Log.d(TAG, "onCreate1: $uri")
        Log.d(TAG, "onCreate2: $bitmap")

        if (uri != null) {
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(
                    contentResolver, Uri.parse(uri)
                )
                //for PhotoEditorView
//                imgResolution.source.setImageBitmap(bitmap)
                //for imageView
                imgResolution.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            Log.d(EditImageActivity.TAG, "onCreate123: $bitmap")
            // for PhotoEditorView
//            imgResolution.source.setImageBitmap(bitmap)
            // for imageView
            imgResolution.setImageBitmap(bitmap)
        }
        imgResolution.setOnClickListener {
        }
    }

    override fun onClick(p0: View?) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
    }

    override fun dispatchTouchEvent(motionEvent: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {

            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 20.0f))
            imgResolution.scaleX = scaleFactor
            imgResolution.scaleY = scaleFactor
            return true
        }
    }
}