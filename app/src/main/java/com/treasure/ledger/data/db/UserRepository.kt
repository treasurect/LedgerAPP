package com.treasure.ledger.data.db

import com.treasure.ledger.LedgerAPP
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(): UserRepository {
            return INSTANCE?.let {
                it
            } ?: kotlin.run {
                INSTANCE = UserRepository()
                INSTANCE!!
            }
        }
    }

    private val userDao = AppDatabase.getInstance(LedgerAPP.context).userDao()

    suspend fun insertUser(user: UserEntity) = withContext(Dispatchers.IO) {
        userDao.insertUser(user)
    }

    suspend fun getUserEntity(
        uid: String? = null, username: String? = null, password: String? = null
    ) = withContext(Dispatchers.IO) {
        uid?.let { uid ->
            userDao.getUserByUId(uid)
        } ?: kotlin.run {
            username?.let { name ->
                password?.let { pwd ->
                    userDao.getUser(name, pwd)
                } ?: userDao.getUserByUsername(name)
            } ?: null
        }
    }

    suspend fun getUserList() = withContext(Dispatchers.IO) {
        userDao.getAllUsers()
    }
}