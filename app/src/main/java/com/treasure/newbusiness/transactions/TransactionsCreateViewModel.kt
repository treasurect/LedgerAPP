package com.treasure.newbusiness.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.treasure.newbusiness.data.db.AppDatabase
import com.treasure.newbusiness.data.db.Transaction
import com.treasure.newbusiness.data.respository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionsCreateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TransactionRepository

    init {
        repository = TransactionRepository(AppDatabase.getDatabase(application).transactionDao())
    }

    fun addTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }
}