package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: FoodAdapter
    private val foodList = ArrayList<ItemClass>()
    private lateinit var dataFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataFile = File(filesDir, "data.txt")
        loadItems()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = FoodAdapter(foodList)
        recyclerView.adapter = userAdapter

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val food = data.getStringExtra("FOOD")
            val calorie = data.getStringExtra("CALORIE")

            val newItem = ItemClass(food!!, calorie!!)
            foodList.add(newItem)
            userAdapter.notifyDataSetChanged()
            saveItems()
        }
    }


    fun loadItems() {
        try {
            foodList.clear()
            val lines = FileUtils.readLines(dataFile, Charset.defaultCharset())
            for (line in lines) {
                val parts = line.split(":")
                val food = parts[0]
                val calorie = parts[1]
                val item = ItemClass(food, calorie)
                foodList.add(item)
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }
//    fun getDataFile(): File{
//
//        return File(filesDir, "data.txt")
//    }

    fun saveItems() {
        val lines = mutableListOf<String>()
        for (food in foodList) {
            val line = "${food.foodName}:${food.caloriesCount}"
            lines.add(line)
        }

        try {
            FileUtils.writeLines(dataFile, lines)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}