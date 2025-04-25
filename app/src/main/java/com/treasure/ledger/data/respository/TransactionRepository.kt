package com.treasure.ledger.data.respository

import com.treasure.ledger.data.entity.TransactionEntity
import com.treasure.ledger.data.dao.TransactionDao

class TransactionRepository(private val dao: TransactionDao) {

    suspend fun getAllTransactions(): List<TransactionEntity> {
        return dao.getAllTransactions()
    }

    suspend fun getRecentlyTransactions(): List<TransactionEntity> {
        return dao.getRecentTransactions()
    }

    suspend fun insert(transaction: TransactionEntity) {
        dao.insertTransaction(transaction)
    }

    suspend fun delete(transaction: TransactionEntity) {
        dao.deleteTransaction(transaction)
    }
}
