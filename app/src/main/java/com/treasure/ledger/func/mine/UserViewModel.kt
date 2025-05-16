package com.treasure.ledger.func.mine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.treasure.basic.utils.SpUtils
import com.treasure.ledger.data.entity.UserEntity
import com.treasure.ledger.utils.Constants

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _userInfo = MutableLiveData<UserEntity?>()
    val userInfo: LiveData<UserEntity?> get() = _userInfo

    fun loadUser() {
//        viewModelScope.launch {
//            _userInfo.postValue(repository.getUser())
//        }
    }

    fun logout() {
        SpUtils.putString(Constants.KEY_SP_LOGIN_UID, "")
//        viewModelScope.launch {
//            repository.deleteUser()
//            _userInfo.postValue(null)
//        }
    }
}

