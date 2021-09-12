package com.example.manage.view.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.manage.R
import com.example.manage.adapter.ExpenseAdapter
import com.example.manage.adapter.onItemCkick
import com.example.manage.model.Expense
import com.example.manage.viewmodel.ExpenseViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.fragment_more.view.*
import kotlinx.android.synthetic.main.show_expense_card.view.*
import java.time.LocalDate
import java.util.*


class More : Fragment(), onItemCkick {

    lateinit var expenseViewModel:ExpenseViewModel
    lateinit var con : Context
    private lateinit var sheetDialog: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        con = container!!.context
        var view = inflater.inflate(R.layout.fragment_more, container, false)

        view.expenseList.layoutManager = LinearLayoutManager(con)
        var adapter = ExpenseAdapter(this)
        view.expenseList.adapter = adapter

        expenseViewModel = ViewModelProvider(requireActivity()).get(ExpenseViewModel::class.java)
        expenseViewModel.monthlyExpense.observe(requireActivity(), {
            if(it.isEmpty()){
                view.noData.visibility = View.VISIBLE
                view.expenseList.visibility = View.GONE
            }
            else{
                it?.let {
                    adapter.updateList(it)
                }
            }
        })
        return view
    }

    override fun clickListener(view: View, expense:Expense) {
        view.cardView.setOnClickListener {
            view.expandable.visibility = if (view.expandable.visibility == View.VISIBLE){
                View.GONE
            } else{
                View.VISIBLE
            }
        }

        view.deleteExpense.setOnClickListener{
            expenseViewModel.deleteExpense(expense)
        }

        view.editExpense.setOnClickListener {
            handleBottomSheet(expense)
        }
    }

    private fun handleBottomSheet(expense:Expense){
        sheetDialog = BottomSheetDialog(con, R.style.BottomStyle)
        val bottomSheetView = LayoutInflater.from(con).inflate(R.layout.bottom_sheet_dialog, null)

        val title = bottomSheetView.findViewById<TextView>(R.id.categoryTitle)
        val amount = bottomSheetView.findViewById<TextView>(R.id.amount)
        val datePickerButton = bottomSheetView.findViewById<ImageView>(R.id.datePicker)

        title.text = expense.category
        amount.text = expense.amount
        val c = Calendar.getInstance()
        var myear = c.get(Calendar.YEAR)
        var mmonth = c.get(Calendar.MONTH)
        var mday = c.get(Calendar.DAY_OF_MONTH)
        datePickerButton.setOnClickListener {
            val dpd = DatePickerDialog(con, { _, year, monthOfYear, dayOfMonth ->
                Toast.makeText(context, "$dayOfMonth $monthOfYear, $year", Toast.LENGTH_SHORT).show()
                myear = year
                mmonth = monthOfYear
                mday = dayOfMonth
            }, myear, mmonth, mday)
            dpd.show()
        }

        bottomSheetView.one.setOnClickListener {
            checkNumber(bottomSheetView, "1")
        }
        bottomSheetView.two.setOnClickListener {
            checkNumber(bottomSheetView, "2")
        }
        bottomSheetView.three.setOnClickListener {
            checkNumber(bottomSheetView, "3")
        }
        bottomSheetView.four.setOnClickListener {
            checkNumber(bottomSheetView, "4")
        }
        bottomSheetView.five.setOnClickListener {
            checkNumber(bottomSheetView, "5")
        }
        bottomSheetView.six.setOnClickListener {
            checkNumber(bottomSheetView, "6")
        }
        bottomSheetView.seven.setOnClickListener {
            checkNumber(bottomSheetView, "7")
        }
        bottomSheetView.eight.setOnClickListener {
            checkNumber(bottomSheetView, "8")
        }
        bottomSheetView.nine.setOnClickListener {
            checkNumber(bottomSheetView, "9")
        }
        bottomSheetView.zero.setOnClickListener {
            checkNumber(bottomSheetView, "0")
        }
        bottomSheetView.delete.setOnClickListener {
            var previous:String = bottomSheetView.amount.text as String
            if(previous != "0"){
                if(previous.length == 1){
                    bottomSheetView.amount.text = "0"
                }
                else{
                    bottomSheetView.amount.text  = previous.substring(0, previous.length - 1)
                }
            }
        }

        bottomSheetView.insert.setOnClickListener {
            expense.amount = amount.text as String
            expense.date = LocalDate.of(myear, mmonth, mday)
            expenseViewModel.updateExpense(expense)
            Toast.makeText(con, "Presses OK", Toast.LENGTH_SHORT).show()
            sheetDialog.dismiss()
        }


        sheetDialog.setContentView(bottomSheetView)
        sheetDialog.show()
    }
    private fun checkNumber(bottomSheetView: View, value : String){
        val previous:String = bottomSheetView.amount.text as String
        if(previous == "0"){
            bottomSheetView.amount.text  = value
        }
        else{
            bottomSheetView.amount.text  = previous + value
        }
    }

}