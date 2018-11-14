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
    val pattern = (if (time.length > 8) "HH:mm:ssXXX" else if (time.length > 5) "HH:mm:ss" else "HH:mm")
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    val times = Time(formatter.parse(time).time).toString().split(':')
    return "${times[0]}:${times[1]}"
}

fun replaceColonWithNewLine(string: String): String {
    return string.replace(Regex("[;]\\s?"), "\n").trim()
}