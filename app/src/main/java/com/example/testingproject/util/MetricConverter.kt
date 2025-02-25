package com.example.testingproject.util

import android.content.Context
import android.util.TypedValue


object MetricConverter {

    fun convertToDp(context: Context, value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}