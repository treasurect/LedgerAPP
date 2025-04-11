package com.treasure.newbusiness.page_main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.treasure.newbusiness.data.db.AppDatabase
import com.treasure.newbusiness.data.db.Transaction
import com.treasure.newbusiness.data.respository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TransactionRepository
    val transactions: MutableLiveData<List<Transaction>> = MutableLiveData()

    init {
        val dao = AppDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(dao)
    }

    fun addTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }

    fun removeTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.delete(transaction)
    }

    fun getAllTransactionsList() = viewModelScope.launch {
        transactions.postValue(repository.getAllTransactions())
    }

    fun getRecentlyTransactionsList() = viewModelScope.launch {
        transactions.postValue(repository.getRecentlyTransactions())
    }
}
