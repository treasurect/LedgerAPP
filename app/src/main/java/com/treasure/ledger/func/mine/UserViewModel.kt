package com.treasure.ledger.func.mine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.UserEntity
import com.treasure.ledger.data.respository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getInstance(application).userDao()
    private val repository = UserRepository(userDao)

    private val _userInfo = MutableLiveData<UserEntity?>()
    val userInfo: LiveData<UserEntity?> get() = _userInfo

    fun loadUser() {
        viewModelScope.launch {
            _userInfo.postValue(repository.getUser())
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.deleteUser()
            _userInfo.postValue(null)
        }
    }
}

