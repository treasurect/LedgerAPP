package com.treasure.ledger.func.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.TransactionEntity
import com.treasure.ledger.data.respository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TransactionRepository
    val transactions: MutableLiveData<List<TransactionEntity>> = MutableLiveData()

    init {
        val dao = AppDatabase.getInstance(application).transactionDao()
        repository = TransactionRepository(dao)
    }

    fun addTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        repository.insert(transaction)
    }

    fun removeTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        repository.delete(transaction)
    }

    fun getAllTransactionsList() = viewModelScope.launch {
        transactions.postValue(repository.getAllTransactions())
    }

    fun getRecentlyTransactionsList() = viewModelScope.launch {
        transactions.postValue(repository.getRecentlyTransactions())
    }
}
