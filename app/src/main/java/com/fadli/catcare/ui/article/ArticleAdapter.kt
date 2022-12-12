package com.fadli.catcare.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R

class ArticleAdapter (private val article: ArrayList<Article>): RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_article, parent, false)
        return  ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val articles = article[position]
        holder.imgArticle.setImageResource(articles.imgArticle)
        holder.nameArticle.text = articles.nameArticle
        holder.descriptionArticle.text = articles.descriptionArticle

        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(article[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = article.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgArticle: ImageView = itemView.findViewById(R.id.img_article)
        val nameArticle: TextView = itemView.findViewById(R.id.judul_article)
        val descriptionArticle: TextView = itemView.findViewById(R.id.description_article)
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }


}