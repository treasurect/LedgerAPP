package com.treasure.ledger.data.db

import com.treasure.ledger.LedgerAPP
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.TransactionEntity

class TransactionRepository {

    companion object {
        @Volatile
        private var INSTANCE: TransactionRepository? = null
        fun getInstance(): TransactionRepository {
            return INSTANCE?.let {
                it
            } ?: kotlin.run {
                INSTANCE = TransactionRepository()
                INSTANCE!!
            }
        }
    }

    private val transactionDao = AppDatabase.getInstance(LedgerAPP.context).transactionDao()

    suspend fun getAllTransactions(uid: String): List<TransactionEntity> {
        return transactionDao.getAllTransactions(uid)
    }

    suspend fun getRecentlyTransactions(uid: String): List<TransactionEntity> {
        return transactionDao.getRecentTransactions(uid)
    }

    suspend fun insert(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun delete(transaction: TransactionEntity) {
        transactionDao.deleteTransaction(transaction)
    }
}
