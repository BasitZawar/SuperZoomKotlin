package com.lads.superzoomkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import com.lads.superzoomkotlin.R

class LuncherActivity : AppCompatActivity() {
    private lateinit var btnGetStarted: AppCompatButton
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luncher)

        btnGetStarted = findViewById(R.id.btnGetStarted)
        progressBar = findViewById(R.id.progressBar)

        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.isVisible = false
            btnGetStarted.isVisible = true
        }, 3000)

        btnGetStarted?.setOnClickListener {
            val intent = Intent(
                this, MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }
    }
}