package com.example.manage.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.manage.R
import com.example.manage.model.Expense
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class ExpenseAdapter(var listener : onItemCkick) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private val expenseList : ArrayList<Expense> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.show_expense_card, parent, false)
        var vh =  ExpenseViewHolder(view)
        vh.itemView.setOnClickListener {
            listener.clickListener(view, expenseList[vh.adapterPosition])
        }
        return vh
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        var currentExpense : Expense = expenseList[position]
        var formattedDate  :String = getFormattedDate(currentExpense.date)
        holder.expenseTitle.text  = currentExpense.amount
        holder.expenseCategory.text = currentExpense.category
        holder.expenseDate.text = formattedDate
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var expenseTitle: TextView = itemView.findViewById(R.id.expenseTitle)
        var expenseCategory: TextView = itemView.findViewById(R.id.expenseCategory)
        var expenseDate: TextView = itemView.findViewById(R.id.expenseDate)
    }

    fun updateList(newExpenseList : List<Expense>) {
        expenseList.clear()
        expenseList.addAll(newExpenseList)

        notifyDataSetChanged()
    }

    private fun getFormattedDate(currentDate : LocalDate) : String{
        var year = currentDate.year
        var month = currentDate.month + 1
        var day = currentDate.dayOfMonth
        return "$day $month $year"
    }
}

interface onItemCkick{
    fun clickListener(view:View, expense:Expense)
}