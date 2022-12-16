package com.skyblue.sqllitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below method create database
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                MOBILE_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        if (db != null) {
            db.execSQL(query)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // below method adding data to database
    fun addDataToDatabase(name: String, mobile: String){
        val values = ContentValues()

        values.put(NAME_COl, name)
        values.put(MOBILE_COL, mobile)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getData(): Cursor?{

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    companion object{

        private val DATABASE_NAME = "TEST_DB"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "test_table"

        val ID_COL = "id"

        val NAME_COl = "name"

        val MOBILE_COL = "mobile"
    }
}