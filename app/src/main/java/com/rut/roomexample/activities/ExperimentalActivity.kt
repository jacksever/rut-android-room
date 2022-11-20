package com.rut.roomexample.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.rut.roomexample.App
import com.rut.roomexample.database.UserDao
import com.rut.roomexample.databinding.ExperimentalActivityBinding
import com.rut.roomexample.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExperimentalActivity : AppCompatActivity() {
    private lateinit var binding: ExperimentalActivityBinding
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExperimentalActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = (application as App).getDb().userDao()

        lifecycleScope.launch {
            userDao.getAllLiveData().observe(binding.root.findViewTreeLifecycleOwner()) {
                binding.data.text = it.toString()
            }
        }

        binding.addUser.setOnClickListener {
            lifecycleScope.launch {
                userDao.insert(
                    User.from(
                        name = "TestName",
                        surname = "TestSurname",
                        email = "test@rut-miit.ru",
                        age = 21,
                        password = "12345678"
                    )
                )
            }
        }

        binding.deleteUser.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val user = userDao.getAll().last()
                userDao.delete(user)
            }
        }
    }

    private fun View.findViewTreeLifecycleOwner(): LifecycleOwner =
        ViewTreeLifecycleOwner.get(this)!!
}
