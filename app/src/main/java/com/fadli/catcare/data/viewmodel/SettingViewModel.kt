package com.fadli.catcare.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fadli.catcare.utils.Constanta
import com.fadli.catcare.utils.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SettingPreferences): ViewModel() {

    // simplify single invocation of preferences with property params
    fun getUserPreferences(property:String): LiveData<String> {
        return when(property){
            Constanta.UserPreferences.UserUID.name -> pref.getUserUid().asLiveData()
            Constanta.UserPreferences.UserToken.name -> pref.getUserToken().asLiveData()
            Constanta.UserPreferences.UserName.name -> pref.getUserName().asLiveData()
            Constanta.UserPreferences.UserEmail.name -> pref.getUserEmail().asLiveData()
            else -> pref.getUserUid().asLiveData()
        }
    }

    fun setUserPreferences(userToken: String, userUid: String, userName:String, userEmail: String) {
        viewModelScope.launch {
            pref.saveLoginSession(userToken,userUid,userName,userEmail)
        }
    }

    fun clearUserPreferences() {
        viewModelScope.launch {
            pref.clearLoginSession()
        }
    }

}