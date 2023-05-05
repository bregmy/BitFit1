package com.example.bitfit

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
private  val TAG = "NewActivity"
class FoodAdapter(private val foodList: ArrayList<ItemClass>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemactivity, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foodList[position]
        // Set item views based on views and data model
        holder.FoodNameView.text = item.foodName
        holder.FoodCountView.text = item.caloriesCount

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val FoodNameView: TextView = itemView.findViewById(R.id.FoodName)
        val FoodCountView: TextView = itemView.findViewById(R.id.FoodCount)
    }
}