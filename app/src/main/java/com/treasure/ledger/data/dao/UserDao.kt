package com.treasure.ledger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.treasure.ledger.data.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_info WHERE uid = 0")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM user_info")
    suspend fun deleteUser()
}
