package com.fadli.catcare.ui.menuCare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R

class CareAdapter (private val care: ArrayList<Care>) : RecyclerView.Adapter<CareAdapter.CareViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_care, parent, false)
        return CareViewHolder(view)
    }

    override fun onBindViewHolder(holder: CareViewHolder, position: Int) {
        val(nameCare, imgCare) = care[position]
        holder.imgCareView.setImageResource(imgCare)
        holder.nameCareView.text = nameCare
    }

    override fun getItemCount(): Int = care.size

    class CareViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView){
        var imgCareView : ImageView = itemView.findViewById(R.id.img_care)
        var nameCareView : TextView = itemView.findViewById(R.id.name_care)

    }
}