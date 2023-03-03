package com.lads.superzoomkotlin.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.lads.superzoomkotlin.EditImageActivity
import com.lads.superzoomkotlin.R


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgCamera: ImageView = findViewById(R.id.imgCamera)
        imgCamera.setOnClickListener(this)

        val imgGallery: ImageView = findViewById(R.id.imgGallery)
        imgGallery.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.imgCamera -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, EditImageActivity.CAMERA_REQUEST)
            }
            R.id.imgGallery -> {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(intent, EditImageActivity.PICK_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_REQUEST && resultCode == RESULT_OK) {
            uri = data?.data
            Log.d("SAAAAA", "onCreate imageUri::${uri.toString()}")

            val intent = Intent(this, SelectedEditingType::class.java)
                .putExtra("pickedImage", uri.toString())
            startActivity(intent)
        } else if (requestCode == CAMERA_REQUEST) {
            val photo = data?.extras?.get("data") as Bitmap?
            Log.d("SBBBBB", "onCreate image::${photo.toString()}")

            val intent = Intent(this, SelectedEditingType::class.java)
                .putExtra("capturedImage", photo)
            startActivity(intent)
//            mPhotoEditorView?.source?.setImageBitmap(photo)
        }
    }


    companion object {
        const val CAMERA_REQUEST = 52
        const val PICK_REQUEST = 53
    }

}