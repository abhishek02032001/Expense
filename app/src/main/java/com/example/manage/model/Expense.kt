package com.example.manage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "expense")
class Expense(@ColumnInfo(name = "amount") var amount: String, @ColumnInfo(name = "category") var category: String, @ColumnInfo(name = "date") var date: LocalDate) {
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
