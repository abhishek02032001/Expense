package com.example.manage.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.manage.R
import com.example.manage.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.app.DatePickerDialog
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.manage.adapter.HandleItemClick
import com.example.manage.model.CategoryModel
import com.example.manage.model.Expense
import com.example.manage.viewmodel.ExpenseViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import java.time.LocalDate
import java.util.*


class Home : Fragment(), HandleItemClick {

    private lateinit var sheetDialog: BottomSheetDialog
    lateinit var con: Context
    lateinit var expenseViewModel : ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        con = container!!.context
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        expenseViewModel = ViewModelProvider(requireActivity()).get(ExpenseViewModel::class.java)

        view.categoryList.layoutManager = GridLayoutManager(activity,3)
        val adapter = CategoryAdapter(con, this)
        view.categoryList.adapter = adapter

        return view
    }

    override fun itemClickListener(currentCategory: CategoryModel) {
        handleBottomSheet(currentCategory)
    }

    private fun handleBottomSheet(currentCategory : CategoryModel){
        sheetDialog = BottomSheetDialog(con, R.style.BottomStyle)
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog, null)

        val title = bottomSheetView.findViewById<TextView>(R.id.categoryTitle)
        val datePickerButton = bottomSheetView.findViewById<ImageView>(R.id.datePicker)

        title.text = currentCategory.title

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
            var c = Calendar.getInstance()
            val amount = bottomSheetView.amount.text as String
            val category = currentCategory.title
            expenseViewModel.insertExpense(Expense(amount, category, LocalDate.of(myear, mmonth, mday)))
            Toast.makeText(con, "$myear $mmonth $mday", Toast.LENGTH_LONG).show()
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