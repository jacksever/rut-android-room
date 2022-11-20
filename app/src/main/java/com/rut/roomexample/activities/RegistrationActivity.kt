package com.rut.roomexample.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rut.roomexample.App
import com.rut.roomexample.databinding.RegistrationActivityBinding
import com.rut.roomexample.entities.User
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: RegistrationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registration.setOnClickListener {
            val name = binding.name.text.toString()
            val surname = binding.surname.text.toString()
            val age = binding.age.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (name.isEmpty() || surname.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Some fields is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val db = (application as App).getDb()
                val user = User.from(name, surname, age.toInt(), email, password)
                db.userDao().insert(user)
            }

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}