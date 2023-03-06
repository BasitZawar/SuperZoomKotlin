package com.lads.superzoomkotlin.activities

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jsibbold.zoomage.ZoomageView
import com.lads.superzoomkotlin.EditImageActivity
import com.lads.superzoomkotlin.R
import java.io.IOException


class ImageResolution : AppCompatActivity(), View.OnClickListener {
    private var bitmap: Bitmap? = null
    private lateinit var imgResolution: ZoomageView
    private var optionsView: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_resolution)

        imgResolution = findViewById(R.id.imgResolution)
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

}