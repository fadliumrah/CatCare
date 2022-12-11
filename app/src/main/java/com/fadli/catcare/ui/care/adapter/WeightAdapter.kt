package com.fadli.catcare.ui.care.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R


class WeightAdapter(private val weight: ArrayList<Weight>) : RecyclerView.Adapter<WeightAdapter.WeightViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_weight, parent, false)
        return WeightViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeightViewHolder, position: Int) {
        val(_titleWeight, _descWeight, _monthWeight, _dayWeight, _dateWeight, _valueWeight) = weight[position]
        holder.titleWeights.text = _titleWeight
        holder.descWeights.text = _descWeight
        holder.monthWeights.text = _monthWeight
        holder.dayWeights.text = _dayWeight
        holder.dateWeights.text = _dateWeight
        holder.valueWeights.text = _valueWeight
    }

    override fun getItemCount(): Int = weight.size

    class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titleWeights : TextView = itemView.findViewById(R.id.title_weight)
        var descWeights : TextView = itemView.findViewById(R.id.schedule_weight)
        var monthWeights : TextView = itemView.findViewById(R.id.tv_month)
        var dayWeights : TextView = itemView.findViewById(R.id.tv_day)
        var dateWeights : TextView = itemView.findViewById(R.id.tv_date)
        var valueWeights : TextView = itemView.findViewById(R.id.value_weight)

    }

}