package com.example.rizkyekoputra.footballmatchschedule

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

object DateHelper {
    fun formatDateToString(date: Date): String {
        return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(date)
    }
}

object StringHelper {
    fun replaceColonWithNewLine(string: String): String {
        return string.replace(Regex("[;]\\s?"), "\n")
    }
}