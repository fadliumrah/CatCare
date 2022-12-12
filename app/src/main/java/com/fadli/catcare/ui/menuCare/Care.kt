package com.fadli.catcare.ui.menuCare

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Care(
    var nameCare : String,
    var imgCare : Int
): Parcelable
