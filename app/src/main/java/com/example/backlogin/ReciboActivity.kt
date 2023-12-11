package com.example.backlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.backlogin.databinding.ActivityReciboBinding

class ReciboActivity : AppCompatActivity(){

    private lateinit var binding: ActivityReciboBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReciboBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val okButton:Button = findViewById(R.id.okButton)
        okButton.setOnClickListener{
            val intent = Intent(this@ReciboActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val nombreUsuario = intent.getStringExtra("nombreUsuario")
        val numeroTarjeta = intent.getStringExtra("numeroTarjeta")
        val montoPagado = intent.getDoubleExtra("montoPagado",0.0)

        binding.tvNombre.text = "$nombreUsuario"
        binding.tvNumeroTarjetaRecibo.text = "$numeroTarjeta"
        binding.tvMontoRecibo.text = "$$montoPagado"
    }
}