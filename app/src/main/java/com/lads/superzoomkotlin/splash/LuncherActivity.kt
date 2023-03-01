package com.lads.superzoomkotlin.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lads.superzoomkotlin.MainActivity
import com.lads.superzoomkotlin.R

class LuncherActivity : AppCompatActivity() {
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luncher)
        button = findViewById(R.id.btnGetStarted)


        button?.setOnClickListener {
            val intent = Intent(
                this, MainActivity::class.java
            )
            startActivity(intent)
        }
    }
}