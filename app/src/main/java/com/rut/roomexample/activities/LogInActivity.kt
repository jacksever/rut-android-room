package com.rut.roomexample.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rut.roomexample.databinding.LogInActivityBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: LogInActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val surname = intent.getStringExtra("surname")

        if (name.isNullOrEmpty()) {
            Toast.makeText(this, "Name is empty!", Toast.LENGTH_SHORT).show()
        } else {
            binding.name.text = "$name $surname"
        }

        binding.exit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}