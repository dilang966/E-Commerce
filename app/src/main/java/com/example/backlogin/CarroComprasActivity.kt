package com.example.backlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backlogin.databinding.ActivityCarroComprasBinding

class CarroComprasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarroComprasBinding
    private lateinit var  adapter: AdaptadorCarroCompras
    private var carroCompras = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarroComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val RegreButton: ImageView = findViewById(R.id.RegreButton)
        RegreButton.setOnClickListener/*object : View.OnClickListener*/ {
            val intent = Intent(this@CarroComprasActivity, MainActivity::class.java)
            startActivity(intent)
        }

        carroCompras = intent.getSerializableExtra("carro_compras") as ArrayList<Producto>

        setupRecyclerView()




//        btnRealizarCompra.setOnClickListener{
//            val intent = Intent(this@CarroComprasActivity, PagoActivity::class.java)
//            startActivity(intent)
//        }
        binding.btnRealizarCompra.setOnClickListener{
            irAPago()
        }

        if (carroCompras.isEmpty()){
            val hintTextView: TextView = findViewById(R.id.hintTextView)
            hintTextView.text = "¡El carrito está vacío! Agrega productos para continuar."
        }else {

        }


    }


    fun setupRecyclerView(){
        binding.rvListaCarro.layoutManager = LinearLayoutManager(this)
        adapter = AdaptadorCarroCompras(binding.tvTotal, carroCompras)
        binding.rvListaCarro.adapter = adapter
        }

    private fun irAPago(){
        val montoTotal = adapter.calcularMontoTotal()
        if (carroCompras.isNotEmpty()){
            val intent = Intent(this@CarroComprasActivity, PagoActivity::class.java)
            intent.putExtra("montoTotal", montoTotal)
            startActivity(intent)

        }else{
            Toast.makeText(this, "No tiene productos en el carrito", Toast.LENGTH_SHORT).show()
        }
    }
}