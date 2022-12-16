package com.skyblue.sqllitedatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.skyblue.sqllitedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.addDataBtn.setOnClickListener{
            // created db helper class pass the context
            val db = DBHelper(this, null)

            val name = mainBinding.enterName.text.toString()
            val mobile = mainBinding.enterMobile.text.toString()

            db.addDataToDatabase(name, mobile)

            Toast.makeText(this, "data added", Toast.LENGTH_SHORT).show()

            mainBinding.enterName.text.clear()
            mainBinding.enterMobile.text.clear()
        }

        mainBinding.getData.setOnClickListener{

            // created db helper class pass the context
            val db = DBHelper(this, null)

            val cursor = db.getData()

            cursor!!.moveToFirst()

            mainBinding.txtName.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            mainBinding.txtMobile.append(cursor.getString(cursor.getColumnIndex(DBHelper.MOBILE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                mainBinding.txtName.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                mainBinding.txtMobile.append(cursor.getString(cursor.getColumnIndex(DBHelper.MOBILE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
        }
}