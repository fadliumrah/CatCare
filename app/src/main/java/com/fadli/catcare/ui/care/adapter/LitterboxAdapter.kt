package com.fadli.catcare.ui.care.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R

class LitterboxAdapter(private val litterbox: ArrayList<Litterbox>) : RecyclerView.Adapter<LitterboxAdapter.LitterboxViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LitterboxViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_litterbox, parent, false)
        return LitterboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: LitterboxViewHolder, position: Int) {
        val(_titleLitterbox, _descLitterbox, _monthLitterbox, _dayLitterbox, _dateLitterbox) = litterbox[position]
        holder.titleLitterbox.text = _titleLitterbox
        holder.descLitterbox.text = _descLitterbox
        holder.monthLitterbox.text = _monthLitterbox
        holder.dayLitterbox.text = _dayLitterbox
        holder.dateLitterbox.text = _dateLitterbox
    }

    override fun getItemCount(): Int = litterbox.size

    class LitterboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titleLitterbox : TextView = itemView.findViewById(R.id.title_litterbox)
        var descLitterbox : TextView = itemView.findViewById(R.id.schedule_litterbox)
        var monthLitterbox : TextView = itemView.findViewById(R.id.tv_month)
        var dayLitterbox : TextView = itemView.findViewById(R.id.tv_day)
        var dateLitterbox : TextView = itemView.findViewById(R.id.tv_date)

    }

}