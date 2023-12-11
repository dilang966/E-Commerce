package com.example.backlogin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.backlogin.databinding.ActivityPagoBinding

class PagoActivity : AppCompatActivity(){

    private lateinit var binding: ActivityPagoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRealizarPago.setOnClickListener{
            simularPago()
        }

        val btnRegresar: ImageView = findViewById(R.id.btnRegresar)
        btnRegresar.setOnClickListener{
            val intent = Intent(this@PagoActivity, CarroComprasActivity::class.java)
            startActivity(intent)
        }

        val montoTotal = intent.getDoubleExtra("montoTotal", 0.0)

        binding.tvMontoTotal.text = "Total a Pagar: $$montoTotal"
    }


    private fun simularPago(){
        val montoPagado = intent.getDoubleExtra("montoTotal", 0.0)
        val numeroTarjeta = binding.etNumeroTarjeta.text.toString()
        val mesExpiracion = binding.etMesExpiracion.text.toString()
        val anioExpiracion = binding.etAnioExpiracion.text.toString()
        val cvc = binding.etCvc.text.toString()
        val nombre = binding.etNombre.text.toString()

        if (numeroTarjeta.isEmpty()){
            Toast.makeText(this, "Campo de Número de Tarjeta vacío", Toast.LENGTH_SHORT).show()
            return
        }
        else if (mesExpiracion.isEmpty()){
            Toast.makeText(this, "Campo de Mes de Expiración vacío", Toast.LENGTH_SHORT).show()
            return
        }
        else if (anioExpiracion.isEmpty()){
            Toast.makeText(this, "Campo de Año de Expiración vacío", Toast.LENGTH_SHORT).show()
            return
        }
        else if (cvc.isEmpty()){
            Toast.makeText(this, "Campo de CVC vacío", Toast.LENGTH_SHORT).show()
            return
        }
        else if (nombre.isEmpty()){
            Toast.makeText(this, "Campo de Nombre vacío", Toast.LENGTH_SHORT).show()
            return
        }
        else {
            if (montoPagado != null){
                val intent = Intent(this@PagoActivity, ReciboActivity::class.java)
                intent.putExtra("nombreUsuario", nombre)
                intent.putExtra("numeroTarjeta", numeroTarjeta)
                intent.putExtra("montoPagado", montoPagado)
                startActivity(intent)
            }

        }

    }
}