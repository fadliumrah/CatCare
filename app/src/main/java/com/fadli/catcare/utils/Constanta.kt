package com.fadli.catcare.utils

object Constanta {

    enum class UserPreferences {
        UserUID, UserName, UserEmail, UserToken
    }

    const val preferenceName = "Settings"
    const val preferenceDefaultValue = "Not Set"

    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")


}