package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class NewActivity : AppCompatActivity() {
    private lateinit var foodInputEditText: EditText
    private lateinit var caloriesInputEditText: EditText
    private val foodlist = ArrayList<ItemClass>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)




        foodInputEditText = findViewById(R.id.FoodInput)
        caloriesInputEditText = findViewById(R.id.CaloriesInput)

        val submitButton = findViewById<Button>(R.id.button2)
        submitButton.setOnClickListener {
            val food = foodInputEditText.text.toString()
            val calorie = caloriesInputEditText.text.toString()


            val intent = Intent()
            intent.putExtra("FOOD", food)
            intent.putExtra("CALORIE", calorie)
            setResult(RESULT_OK, intent)
            finish()





        }
    }
}