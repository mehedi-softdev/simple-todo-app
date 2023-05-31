package com.mehedisoftdev.simpletodoapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {
    fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date)
    }
    fun stringDateToDate(dateInString: String): Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.parse(dateInString)
    }
}