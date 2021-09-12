package com.example.manage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.manage.model.Expense
import com.example.manage.model.ExpenseManagerDatabase
import com.example.manage.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    val monthlyExpense : LiveData<List<Expense>>
    private val repository : ExpenseRepository

    init {
        val dao = ExpenseManagerDatabase.getDatabase(application).getExpenseDao()
        repository = ExpenseRepository(dao)
        monthlyExpense = repository.monthlyExpense
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePreviousExpense()
        }
    }

    fun insertExpense(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertExpense(expense)
    }

    fun deleteExpense(expense : Expense) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteExpense(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateExpense(expense)
    }

}