package com.example.rizkyekoputra.footballmatchschedule.Utils

import android.view.View
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formatDateToString(date: Date): String {
    return SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(date)
}

fun formatTimeToString(time: String): String {
    val formatter = SimpleDateFormat("HH:mm:ssXXX", Locale.getDefault())
    val time = Time(formatter.parse(time).time).toString().split(':')
    return "${time[0]}:${time[1]}"
}

fun replaceColonWithNewLine(string: String): String {
    return string.replace(Regex("[;]\\s?"), "\n").trim()
}