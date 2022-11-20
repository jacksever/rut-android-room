package com.rut.roomexample.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rut.roomexample.App
import com.rut.roomexample.databinding.MainActivityBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email or password is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val db = (application as App).getDb()
                val user = db.userDao().getUser(email, password)

                if (user != null) {
                    startActivity(Intent(this@MainActivity, LogInActivity::class.java).apply {
                        putExtra("name", user.name)
                        putExtra("surname", user.surname)
                    })
                } else {
                    Toast.makeText(this@MainActivity, "User not found!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.registration.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
        }

        binding.experimental.setOnClickListener {
            startActivity(Intent(this@MainActivity, ExperimentalActivity::class.java))
        }
    }
}