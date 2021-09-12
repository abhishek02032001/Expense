package com.example.manage.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.AnyChart
import com.example.manage.R
import com.example.manage.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_statistic.view.*


class Statistic : Fragment() {

    lateinit var expenseViewModel :ExpenseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_statistic, container, false)

        val pie = AnyChart.pie()

        val data: MutableList<DataEntry> = ArrayList()

        expenseViewModel = ViewModelProvider(requireActivity()).get(ExpenseViewModel::class.java)
        expenseViewModel.monthlyExpense.observe(requireActivity(), {
            if(it.isNotEmpty()){
                view.noData.visibility = View.GONE
                var hash : MutableMap<String, Int> = mutableMapOf(
                    "Education" to 0,
                    "Groceries" to 0,
                    "Restaurants" to 0,
                    "Friends" to 0,
                    "Pets" to 0,
                    "Sports" to 0,
                    "Bills" to 0,
                    "Public Transit" to 0,
                    "Gifts" to 0,
                    "Vacation" to 0,
                    "Maintenance" to 0,
                    "Other" to 0
                )
                for(i in it){
                    hash[i.category] = hash[i.category]!! + i.amount.toInt()
                }
                for(i in hash){
                    if(i.value != 0)
                        data.add(ValueDataEntry(i.key, i.value))
                }
            }
            else{
                view.noData.visibility = View.VISIBLE
            }

        })

        pie.data(data)

        view.pie_chart_view.setChart(pie)

        return view
    }

}