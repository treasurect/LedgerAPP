package com.treasure.newbusiness.data.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.treasure.newbusiness.data.db.Transaction
import com.treasure.newbusiness.data.db.TransactionDao

class TransactionRepository(private val dao: TransactionDao) {

    suspend fun getAllTransactions(): List<Transaction>{
        return dao.getAllTransactions()
    }

    suspend fun getRecentlyTransactions(): List<Transaction>{
        return dao.getRecentTransactions()
    }

    suspend fun insert(transaction: Transaction) {
        dao.insertTransaction(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        dao.deleteTransaction(transaction)
    }
}
