package com.example.backlogin.DB

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.backlogin.R


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME ,  null, 1) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "LoginDemo.db"
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME("
                +"$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"$COLUMN_USERNAME TEXT,"
                +"$COLUMN_PASSWORD TEXT)")
        if (db != null) {
            db.execSQL(CREATE_TABLE)
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        }
        onCreate(db)
    }

    @SuppressLint("Range")
    fun obtenerNameUser(): String{

        val db = this.readableDatabase
        val query = "SELECT username FROM user LIMIT 1"
        val cursor: Cursor = db.rawQuery(query, null)

        var nombreUsuario = ""

        if (cursor.moveToFirst()){
            nombreUsuario = cursor.getString(cursor.getColumnIndex("username"))
        }

        cursor.close()
        db.close()

        return nombreUsuario
    }

}


class InicioActivity: AppCompatActivity(){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseManager = DatabaseHelper(this)

        val nombreUsuario = databaseManager.obtenerNameUser()

        val usernameTextView = findViewById<TextView>(R.id.textView2)

        usernameTextView.text = nombreUsuario
    }
}






        
      /*  class SQLiteLogin{
            private val database: Database = Database.open("database.sqlite")

            fun login(username: String, password: String){
                runBlocking (Dispatchers.IO) {
                    val cursor = database.rawQuery("SELECT * FROM users WHERE username = ?", arrayOf(username))
                    val user = cursor.fetchOne()

                    if (user == null){
                        println("Usuario o contraseña incorrectos.")
                        return@runBlocking
                    }

                    if (user["password"] != password){
                        println("Usuario o contraseña incorrectos.")
                        return@runBlocking
                    }

                    println("Bienvenido, {}!".format(user["username"]))
                }
            }
        }
        fun main(args: Array<String>){
            val login = SQLiteLogin()

            val username = "user1"
            val password = "password1"

            login.login(username, password)
        }*/



