package com.lads.superzoomkotlin.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.lads.superzoomkotlin.EditImageActivity
import com.lads.superzoomkotlin.R
import com.lads.superzoomkotlin.databinding.ActivitySelectedEditingTypeBinding
import java.io.IOException

class SelectedEditingType : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedEditingTypeBinding
    private var bitmap: Bitmap? = null
    private var imgView: ImageView? = null
//    private var selectedImgFromGallery: ImageView? = null
//    private lateinit var btnArrowBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedEditingTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imgView = findViewById(R.id.selectedImgFromGallery)

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(0xff6b06e0.toInt(), 0xffe13d1c.toInt())
        )
        gradientDrawable.shape = GradientDrawable.OVAL;

        binding.resolution.background = gradientDrawable

        binding.btnArrowBack.setOnClickListener {
            finish()
        }


        var uri = intent.getStringExtra("pickedImage")
        bitmap = intent.getParcelableExtra<Bitmap>("capturedImage")

        binding.resolution?.setOnClickListener {
            val intent = Intent(this, ImageResolution::class.java)
                .putExtra("pickedImage", uri).putExtra("capturedImage", bitmap)
            startActivityForResult(intent, EditImageActivity.CAMERA_REQUEST)
        }

        binding.selectedImgFromGallery?.setOnClickListener {
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