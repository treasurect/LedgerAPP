package com.treasure.ledger.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.treasure.ledger.data.entity.TransactionEntity

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE uid = :uid ORDER BY timestamp DESC")
    suspend fun getAllTransactions(uid: String): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE uid = :uid ORDER BY timestamp DESC LIMIT 5")
    suspend fun getRecentTransactions(uid: String): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)
}
