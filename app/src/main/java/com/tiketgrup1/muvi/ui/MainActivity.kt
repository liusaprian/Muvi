package com.tiketgrup1.muvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tiketgrup1.muvi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(binding.frameContainer.id, BaseFragment())
            .commit()
    }
}