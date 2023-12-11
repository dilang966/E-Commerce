package com.example.backlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.backlogin.DB.UserRepository

class RegistroActivity: AppCompatActivity() {

    private  lateinit var userRepository: UserRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        userRepository = UserRepository(this)

        val userRegisterEditText: EditText = findViewById(R.id.userRegisterEditText)
        val passwordRegisterEditText: EditText = findViewById(R.id.passwordRegisterEditText)
        val loginRegisterButton: Button = findViewById(R.id.loginRegisterButton)
        val loginUser: TextView = findViewById(R.id.loginUser)

        loginRegisterButton.setOnClickListener {
            val username = userRegisterEditText.text.toString()
            val password = passwordRegisterEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val existingUser = userRepository.getUser(username)

                if (existingUser == null) {
                    val userId = userRepository.createUser(username, password)
                    if (userId != -1L) {
                        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Porfavor completa los campos", Toast.LENGTH_SHORT).show()
            }


        }
        loginUser.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
        override fun onDestroy() {
            super.onDestroy()
            userRepository.close()

    }
}