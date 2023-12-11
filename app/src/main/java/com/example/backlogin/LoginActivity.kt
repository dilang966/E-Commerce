package com.example.backlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
/*import com.example.backlogin.DB.InicioActivity*/
import com.example.backlogin.DB.UserRepository

class LoginActivity: AppCompatActivity(){
    private  lateinit var userRepository: UserRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userRepository = UserRepository(this)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val registroButton: TextView = findViewById(R.id.registroButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = userRepository.getUser(username)


            if (user != null && user.password == password){
                Toast.makeText(this, "Inicio de sesion Exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PinActivity::class.java)
                startActivity(intent)


            }else{
                Toast.makeText(this, "Inicio de sesion Fallido", Toast.LENGTH_SHORT).show()
            }

        }

        registroButton.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onDestroy(){
        super.onDestroy()
        userRepository.close()
    }
}