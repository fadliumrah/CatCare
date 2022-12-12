package com.fadli.catcare.ui.article

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val nameArticle : String,
    val descriptionArticle : String,
    val imgArticle : Int
): Parcelable
