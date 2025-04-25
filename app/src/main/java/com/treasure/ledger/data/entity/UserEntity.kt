package com.treasure.ledger.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val phoneNumber: String,
    val nickname: String,
    val level: String,
    val recordDays: Int,
    val recordCount: Int,
    val isLoggedIn: Boolean
)

