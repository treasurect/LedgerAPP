package com.treasure.ledger.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val username: String,
    val password: String,
    val nickname: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val level: String? = null,
    val recordDays: Int? = null,
    val recordCount: Int? = null,
    val createdAt: Long = System.currentTimeMillis()
)

