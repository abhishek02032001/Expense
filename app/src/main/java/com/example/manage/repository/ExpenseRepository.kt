package com.example.manage.repository

import androidx.lifecycle.LiveData
import com.example.manage.model.Expense
import com.example.manage.model.ExpenseDao
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class ExpenseRepository(private val expenseDao : ExpenseDao) {
    val c = Calendar.getInstance()
    var year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    private val instant: Instant = LocalDate.of(year, month, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()
    private val t = instant.toEpochMilli()

    val monthlyExpense : LiveData<List<Expense>> = expenseDao.getMonthlyExpense(t)

    suspend fun insertExpense(expense : Expense){
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense){
        expenseDao.deleteExpense(expense)
    }

    suspend fun updateExpense(expense: Expense){
        expenseDao.updateExpense(expense)
    }

    suspend fun deletePreviousExpense(){
        expenseDao.deletePreviousExpense(t)
    }


}