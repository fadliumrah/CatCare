package com.fadli.catcare.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.fadli.catcare.R
import com.fadli.catcare.ui.dashboard.home.HomeFragment

class DetailArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_article)

        val articles = intent.getParcelableExtra<Article>(HomeFragment.EXTRA_ARTICLE) as Article

        val judulArticle = findViewById<TextView>(R.id.dtJudul)
        val descriptionArticle = findViewById<TextView>(R.id.dtDescription)
        val imgArticle = findViewById<ImageView>(R.id.dtImgArticle)

        imgArticle.setImageResource(articles.imgArticle)
        judulArticle.text = articles.nameArticle
        descriptionArticle.text = articles.descriptionArticle

    }
}