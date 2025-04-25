package com.treasure.ledger.data.respository

import com.treasure.ledger.data.dao.UserDao
import com.treasure.ledger.data.entity.UserEntity

class UserRepository(private val userDao: UserDao) {
    suspend fun getUser() = userDao.getUser()
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    suspend fun deleteUser() = userDao.deleteUser()
}
