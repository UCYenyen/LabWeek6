package com.example.labweek6.ui.viewmodel.soal1

import androidx.lifecycle.ViewModel
import com.example.labweek6.ui.dummy_datas.soal1.PopularMenu
import com.example.labweek6.ui.dummy_datas.soal1.Vegetables
import com.example.labweek6.ui.model.soal1.FoodItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Soal1ViewModel : ViewModel() {

    private val _foodItems = MutableStateFlow<List<FoodItem>>(emptyList())
    val foodItems: StateFlow<List<FoodItem>> = _foodItems.asStateFlow()

    private val _vegetableItems = MutableStateFlow<List<FoodItem>>(emptyList())
    val vegetableItems: StateFlow<List<FoodItem>> = _vegetableItems.asStateFlow()

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        _foodItems.value = PopularMenu.items.toList()

        _vegetableItems.value = Vegetables.items.toList()
    }
}