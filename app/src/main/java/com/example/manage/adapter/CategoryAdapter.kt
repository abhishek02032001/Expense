package com.example.manage.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.manage.R
import com.example.manage.const.CategoryData
import com.example.manage.model.CategoryModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.category_view.view.*
import java.util.*

class CategoryAdapter(var context: Context, var listener : HandleItemClick) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var list: List<CategoryModel> = CategoryData.categoryData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.category_view, parent, false)
        val vh = CategoryViewHolder(view)
        vh.item.setOnClickListener {
            listener.itemClickListener(list[vh.adapterPosition])
        }
        return vh
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var currentCategory : CategoryModel = list[position]
        holder.title.text = currentCategory.title
        holder.image.setImageResource(currentCategory.imgSrc)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var item : View = itemView
        var title : TextView = itemView.title
        var image : ImageView = itemView.categoryImage
    }

    fun updateList(l : List<CategoryModel>) {
//        list.d
    }
}

interface HandleItemClick {
    fun itemClickListener(currentCategory: CategoryModel)
}