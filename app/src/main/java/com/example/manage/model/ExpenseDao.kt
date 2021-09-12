package com.example.manage.model

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ExpenseDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("Select * from expense")
    fun getAllExpense() : LiveData<List<Expense>>

    @Query("select * from expense where date >= :t")
    fun getMonthlyExpense(t : Long) : LiveData<List<Expense>>

    @Query("Delete from expense where date < :t")
    fun deletePreviousExpense(t : Long)

}