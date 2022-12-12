package com.fadli.catcare.ui.dataCat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    var nameCat : String,
    var rasCat : String,
    var colorCat : String,
    var imgCat : Int,
    var jkCat : String,
    var umurCat : String
):Parcelable
