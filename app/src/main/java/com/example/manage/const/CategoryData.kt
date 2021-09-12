package com.example.manage.const

import com.example.manage.R
import com.example.manage.model.CategoryModel

object CategoryData {
    var categoryData : List<CategoryModel> = listOf(
        CategoryModel("Groceries", R.drawable.groceries),
        CategoryModel("Restaurants", R.drawable.restaurant),
        CategoryModel("Education", R.drawable.education),
        CategoryModel("Friends", R.drawable.friends),
        CategoryModel("Pets", R.drawable.pet),
        CategoryModel("Sports", R.drawable.sports),
        CategoryModel("Bills", R.drawable.bill),
        CategoryModel("Public Transit", R.drawable.public_transit),
        CategoryModel("Gifts", R.drawable.gift),
        CategoryModel("Vacation", R.drawable.vacation),
        CategoryModel("Maintenance", R.drawable.maintenance),
        CategoryModel("Other", R.drawable.other),
    )
}