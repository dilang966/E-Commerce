package com.example.backlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random
import java.util.Timer
import java.util.TimerTask
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

/*import kotlin.random.Random*/

val random = Random()

class PinActivity: AppCompatActivity() {

    private lateinit var generarCode: TextView
    private lateinit var timeTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private val timer = Timer()
    private val handler = Handler()
    private var secondsPassed: Long = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        val editTextNumber:EditText = findViewById(R.id.code)
        val flechaBack: ImageView = findViewById(R.id.flechaBack)


        generarCode = findViewById(R.id.generarCode)
        timeTextView = findViewById(R.id.timeTextView)


        val randomCode = generateRandomCode()
        generarCode.text = randomCode

        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsPassed = millisUntilFinished / 1000
                timeTextView.text = "$secondsPassed"
            }

            override fun onFinish() {

                start()
            }
        }.start()


        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                handler.post{
                    val newPin = generateRandomCode()
                    val currentTime = getCurrentTime()
                    generarCode.text = newPin
                    secondsPassed += 30


                }
            }
        }, 0, 30000)


        /*Ingreso de inmediato*/
        editTextNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == generarCode.text.toString().length) {
                    if (s.toString() == generarCode.text.toString()) {
                        val intent = Intent(this@PinActivity, MainActivity::class.java)
                        startActivity(intent)

                        editTextNumber.removeTextChangedListener(this)

                    } else {
                        Toast.makeText(this@PinActivity, "Pin incorrecto", Toast.LENGTH_SHORT).show()
                        editTextNumber.text.clear()
                    }
                }
            }
        })

        /*Boton regresar*/
        flechaBack.setOnClickListener/*object : View.OnClickListener*/ {
            val intent = Intent(this@PinActivity, LoginActivity::class.java)
            startActivity(intent)


            //Anterior verificacion con boton

            /*override fun onClick(v: View) {
                val editTextNumber = editTextNumber.text.toString()
                *//*val correctCode = "12345"*//*

                if (editTextNumber == generarCode.text.toString()) {
                    val intent = Intent(this@PinActivity, InicioActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@PinActivity, "Exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@PinActivity, "Error", Toast.LENGTH_SHORT).show()
                }

            }*/

        }


    }

        /*Generacion de Codigo aleatorio*/
        private fun generateRandomCode(): String {
            val pin = StringBuilder()
            repeat(5) {
                val digit = random.nextInt(10)
                pin.append(digit)
            }

            return pin.toString()
            /*Otra forma de generar el codigo*/
            /*val random = Random(System.currentTimeMillis())
        val code = StringBuilder ()

        for (i in 1 .. 5){
            val random = random.nextInt(0,10)
            code.append(random)
        }*/

        }

        /*Generacion de segundos*/
        private fun getCurrentTime(): String{
        val sdf = SimpleDateFormat("ss")
        val currentTime = sdf.format(Date())
        return currentTime
        }
}
