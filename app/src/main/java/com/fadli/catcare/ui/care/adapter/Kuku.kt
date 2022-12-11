package com.fadli.catcare.ui.care.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kuku(
    val titleNail : String,
    val scheduleNail : String,
    val monthNail : String,
    val dayNail : String,
    val dateNail : String,
): Parcelable

@Parcelize
data class Weight(
    val titleWeight : String,
    val scheduleWeight : String,
    val monthWeight : String,
    val dayWeight : String,
    val dateWeight : String,
    val valueWeight : String,
): Parcelable

@Parcelize
data class Fleas(
    val titleFleas : String,
    val scheduleFleas : String,
    val monthFleas : String,
    val dayFleas : String,
    val dateFleas : String,
    val valueDose : String,
    val valueAge : String,
): Parcelable

@Parcelize
data class Litterbox(
    val titleLitterbox : String,
    val scheduleLitterbox : String,
    val monthLitterbox : String,
    val dayLitterbox : String,
    val dateLitterbox : String,
): Parcelable