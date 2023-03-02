package com.lads.superzoomkotlin.activities

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lads.superzoomkotlin.EditImageActivity
import com.lads.superzoomkotlin.R
import ja.burhanrashid52.photoeditor.PhotoEditorView
import java.io.IOException

class ImageResolution : AppCompatActivity() {
    private var bitmap: Bitmap? = null

    private lateinit var imgResolution: PhotoEditorView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_resolution)

        imgResolution = findViewById(R.id.imgResolution)
        var uri = intent.getStringExtra("pickedImage")
        bitmap = intent.getParcelableExtra<Bitmap>("capturedImage")

        Log.d(TAG, "onCreate1: $uri")
        Log.d(TAG, "onCreate2: $bitmap")

        if (uri != null) {
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(
                    contentResolver, Uri.parse(uri)
                )
                imgResolution.source.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            Log.d(EditImageActivity.TAG, "onCreate123: $bitmap")
            imgResolution.source.setImageBitmap(bitmap)
        }
    }
}