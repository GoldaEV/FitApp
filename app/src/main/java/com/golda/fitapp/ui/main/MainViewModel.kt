package com.golda.fitapp.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*


const val GOAL = 1480
const val BURN = 300

class MainViewModel : ViewModel() {

    private val _listSettlement = MutableStateFlow(ParamsCalculator())
    val listSettlement = _listSettlement.asStateFlow()


    fun applyKcalBreakfast(value: Int) {
        _listSettlement.value = _listSettlement.value.copy(
            breakfast = value,
            breakfastTime = Calendar.getInstance().time.time
        )
        calculate()
    }

    fun applyKcalLunch(value: Int) {
        _listSettlement.value = _listSettlement.value.copy(
            lunch = value,
            lunchTime = Calendar.getInstance().time.time
        )
        calculate()
    }

    fun applyKcalDinner(value: Int) {
        _listSettlement.value = _listSettlement.value.copy(
            dinner = value,
            dinnerTime = Calendar.getInstance().time.time
        )
        calculate()
    }


    private fun calculate() {
        _listSettlement.value = _listSettlement.value.copy(
            eating = _listSettlement.value.breakfast + _listSettlement.value.lunch + _listSettlement.value.dinner,
            total = _listSettlement.value.breakfast + _listSettlement.value.lunch + _listSettlement.value.dinner - _listSettlement.value.burn

        )
    }

}

data class ParamsCalculator(
    val breakfast: Int = 0,
    val breakfastTime: Long? = null,
    val lunch: Int = 0,
    val lunchTime: Long? = null,
    val dinner: Int = 0,
    val dinnerTime: Long? = null,
    val goal: Int = GOAL,
    val eating: Int = 0,
    val burn: Int = BURN,
    val total: Int = 0
)