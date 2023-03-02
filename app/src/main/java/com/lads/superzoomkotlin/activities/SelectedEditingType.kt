package com.lads.superzoomkotlin.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.lads.superzoomkotlin.EditImageActivity
import java.io.IOException


class SelectedEditingType : AppCompatActivity() {
    private var bitmap: Bitmap? = null
    private var imgView: ImageView? = null
    private var resolution: ImageView? = null
    private var selectedImgFromGallery: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lads.superzoomkotlin.R.layout.activity_selected_editing_type)

        imgView = findViewById(com.lads.superzoomkotlin.R.id.selectedImgFromGallery)
        resolution = findViewById(com.lads.superzoomkotlin.R.id.resolution)
        selectedImgFromGallery = findViewById(com.lads.superzoomkotlin.R.id.selectedImgFromGallery)

        var uri = intent.getStringExtra("pickedImage")
        bitmap = intent.getParcelableExtra<Bitmap>("capturedImage")

        resolution?.setOnClickListener {
            val intent = Intent(this, ImageResolution::class.java)
                .putExtra("pickedImage", uri).putExtra("capturedImage", bitmap)
            startActivityForResult(intent, EditImageActivity.CAMERA_REQUEST)
        }
        // 7685 pending bill
        //


        selectedImgFromGallery?.setOnClickListener {
            val intent = Intent(this, EditImageActivity::class.java)
                .putExtra("pickedImage", uri).putExtra("capturedImage", bitmap)
            startActivityForResult(intent, EditImageActivity.CAMERA_REQUEST)
        }

        Log.d(TAG, "onCreate:1214 $uri")
        Log.d(TAG, "onCreate:1215 $bitmap")

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST -> {
                    val photo = data?.extras?.get("data") as Bitmap?
                    imgView?.setImageBitmap(photo)
                    Log.d(EditImageActivity.TAG, "onActivityResult camera:$photo")
                }
                PICK_REQUEST -> try {
                    val uri = data?.data
                    val bitmap = MediaStore.Images.Media.getBitmap(
                        contentResolver, uri
                    )
                    imgView?.setImageBitmap(bitmap)
                    Log.d(EditImageActivity.TAG, "onActivityResultPicked: $bitmap")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    companion object {
        const val CAMERA_REQUEST = 52
        const val PICK_REQUEST = 53
    }
}