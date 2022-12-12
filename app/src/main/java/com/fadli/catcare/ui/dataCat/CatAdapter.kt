package com.fadli.catcare.ui.dataCat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R

class CatAdapter (private val cat: ArrayList<Cat>): RecyclerView.Adapter<CatAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_data_cat, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nameCat, rasCat, colorCat, imgCat) = cat[position]
        holder.tvImgCat.setImageResource(imgCat)
        holder.tvNameCat.text = nameCat
        holder.tvRasCat.text = rasCat
        holder.tvColorCat.text = colorCat
    }

    override fun getItemCount(): Int = cat.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvImgCat: ImageView = itemView.findViewById(R.id.img)
        var tvNameCat: TextView = itemView.findViewById(R.id.name)
        var tvRasCat: TextView = itemView.findViewById(R.id.ras)
        var tvColorCat: TextView = itemView.findViewById(R.id.color)

    }
}