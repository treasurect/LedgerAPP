package com.treasure.ledger.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val type: String, // "收入" or "支出"
    val category: String,
    val remark: String?,
    val timestamp: Long = System.currentTimeMillis()
)