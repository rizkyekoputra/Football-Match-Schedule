package com.example.rizkyekoputra.footballmatchschedule.Utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formatDateToString(date: Date): String {
    return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(date)
}

fun replaceColonWithNewLine(string: String): String {
    return string.replace(Regex("[;]\\s?"), "\n").trim()
}