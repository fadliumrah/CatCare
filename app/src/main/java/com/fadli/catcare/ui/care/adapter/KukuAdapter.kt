package com.fadli.catcare.ui.care.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R


class KukuAdapter(private val kuku: ArrayList<Kuku>) : RecyclerView.Adapter<KukuAdapter.KukuViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KukuViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_kuku, parent, false)
        return KukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: KukuViewHolder, position: Int) {
        val(_titleNail, _descNail, _monthNail, _dayNail, _dateNail) = kuku[position]
        holder.titleNails.text = _titleNail
        holder.descNails.text = _descNail
        holder.monthNails.text = _monthNail
        holder.dayNails.text = _dayNail
        holder.dateNails.text = _dateNail
    }

    override fun getItemCount(): Int = kuku.size

    class KukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titleNails : TextView = itemView.findViewById(R.id.title_kuku)
        var descNails : TextView = itemView.findViewById(R.id.schedule_nails)
        var monthNails : TextView = itemView.findViewById(R.id.tv_month)
        var dayNails : TextView = itemView.findViewById(R.id.tv_day)
        var dateNails : TextView = itemView.findViewById(R.id.tv_date)

    }

}