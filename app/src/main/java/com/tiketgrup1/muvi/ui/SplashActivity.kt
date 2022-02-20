package com.tiketgrup1.muvi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.tiketgrup1.muvi.R
import com.tiketgrup1.muvi.databinding.ActivityMainBinding
import com.tiketgrup1.muvi.databinding.ActivitySplashBinding

public const val SPLASH_TIME = 4000L

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.myLooper()!!).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            SPLASH_TIME
        )

        val fadeAnim = AnimationUtils.loadAnimation(this, R.anim.fade_animation)

        binding.ivSplashLogo.animation = fadeAnim
    }
}