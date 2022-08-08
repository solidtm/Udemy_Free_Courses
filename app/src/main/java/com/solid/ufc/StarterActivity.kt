package com.solid.ufc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.solid.ufc.databinding.ActivityStarterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStarterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}