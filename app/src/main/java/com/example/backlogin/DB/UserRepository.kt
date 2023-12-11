package com.example.backlogin.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.backlogin.DB.DatabaseHelper
import com.example.backlogin.DB.User


class UserRepository(context: Context){
    private val database: SQLiteDatabase = DatabaseHelper(context).writableDatabase

    fun createUser(username: String, password: String): Long {
        val values = ContentValues()
        values.put(DatabaseHelper.COLUMN_USERNAME, username)
        values.put(DatabaseHelper.COLUMN_PASSWORD, password)
        return database.insert(DatabaseHelper.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getUser(username: String): User?{
        val query = "SELECT * FROM ${DatabaseHelper.TABLE_NAME} WHERE ${DatabaseHelper.COLUMN_USERNAME} = ?"
        val cursor: Cursor = database.rawQuery(query, arrayOf(username))

        return if (cursor.moveToFirst()){
            User(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD))
            )
        } else{
            null
        }
    }
    fun close(){
        database.close()
    }
}




/*
val BD="baseDatos";

class BaseDatos(contexto: Context): SQLiteOpenHelper(contexto, BD, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}*/
