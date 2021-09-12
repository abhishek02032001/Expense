package com.example.manage.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.manage.util.Converters

@Database(entities = [Expense :: class], version = 3)
@TypeConverters(Converters::class)
abstract class ExpenseManagerDatabase : RoomDatabase() {

    abstract fun getExpenseDao() : ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseManagerDatabase? = null

        fun getDatabase(context: Context): ExpenseManagerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseManagerDatabase::class.java,
                    "expense_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}