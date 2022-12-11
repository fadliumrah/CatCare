package com.fadli.catcare.ui.care.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R

class FleasAdapter(private val fleas: ArrayList<Fleas>) : RecyclerView.Adapter<FleasAdapter.FleasViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FleasViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_fleas, parent, false)
        return FleasViewHolder(view)
    }

    override fun onBindViewHolder(holder: FleasViewHolder, position: Int) {
        val(_titleFleas, _descFleas, _monthFleas, _dayFleas, _dateFleas, _valueDose, _valueAge) = fleas[position]
        holder.titleFleas.text = _titleFleas
        holder.descFleas.text = _descFleas
        holder.monthFleas.text = _monthFleas
        holder.dayFleas.text = _dayFleas
        holder.dateFleas.text = _dateFleas
        holder.valueDoseFleas.text = _valueDose
        holder.valueAgeFleas.text = _valueAge
    }

    override fun getItemCount(): Int = fleas.size

    class FleasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titleFleas : TextView = itemView.findViewById(R.id.title_fleas)
        var descFleas : TextView = itemView.findViewById(R.id.schedule_fleas)
        var monthFleas : TextView = itemView.findViewById(R.id.tv_month)
        var dayFleas : TextView = itemView.findViewById(R.id.tv_day)
        var dateFleas : TextView = itemView.findViewById(R.id.tv_date)
        var valueDoseFleas : TextView = itemView.findViewById(R.id.value_dose)
        var valueAgeFleas : TextView = itemView.findViewById(R.id.value_age)
    }

}