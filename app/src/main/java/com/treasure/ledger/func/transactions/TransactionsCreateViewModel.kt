package com.treasure.ledger.func.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.TransactionEntity
import com.treasure.ledger.data.respository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionsCreateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TransactionRepository

    init {
        repository = TransactionRepository(AppDatabase.getInstance(application).transactionDao())
    }

    fun addTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        repository.insert(transaction)
    }
}