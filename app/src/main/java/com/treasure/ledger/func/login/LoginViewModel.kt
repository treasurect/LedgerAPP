package com.treasure.ledger.func.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treasure.ledger.data.db.UserRepository
import com.treasure.ledger.data.entity.UserEntity
import com.treasure.ledger.utils.Event
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    private val _loginStatus = MutableLiveData<Event<LoginResult>>()
    val loginStatus: LiveData<Event<LoginResult>> = _loginStatus

    private val _registerStatus = MutableLiveData<Event<RegisterResult>>()
    val registerStatus: LiveData<Event<RegisterResult>> = _registerStatus

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _loginStatus.value = Event(LoginResult.Error("用户名和密码不能为空"))
            return
        }

        viewModelScope.launch {
            try {
                val user = UserRepository.getInstance().getUserEntity(username = username)
                if (user != null) {
                    if (user.password == password) {
                        _loginStatus.value = Event(LoginResult.Success(user))
                    } else {
                        _loginStatus.value = Event(LoginResult.Error("密码错误"))
                    }
                } else {
                    _loginStatus.value = Event(LoginResult.Error("用户不存在"))
                }
            } catch (e: Exception) {
                _loginStatus.value = Event(LoginResult.Error("登录失败: ${e.message}"))
            }
        }
    }

    fun register(username: String, password: String, email: String?) {
        if (username.isBlank() || password.isBlank()) {
            _registerStatus.value = Event(RegisterResult.Error("用户名和密码不能为空"))
            return
        }

        viewModelScope.launch {
            try {
                // 检查用户名是否已存在
                val existingUser = UserRepository.getInstance().getUserEntity(username = username)
                if (existingUser != null) {
                    _registerStatus.value = Event(RegisterResult.Error("用户名已存在"))
                    return@launch
                }

                // 创建新用户
                val newUser = UserEntity(username = username, password = password, email = email)
                UserRepository.getInstance().insertUser(newUser)
                _registerStatus.value = Event(RegisterResult.Success(newUser))
            } catch (e: Exception) {
                _registerStatus.value = Event(RegisterResult.Error("注册失败: ${e.message}"))
            }
        }
    }

    sealed class LoginResult {
        data class Success(val user: UserEntity) : LoginResult()
        data class Error(val message: String) : LoginResult()
    }

    sealed class RegisterResult {
        data class Success(val user: UserEntity) : RegisterResult()
        data class Error(val message: String) : RegisterResult()
    }
}