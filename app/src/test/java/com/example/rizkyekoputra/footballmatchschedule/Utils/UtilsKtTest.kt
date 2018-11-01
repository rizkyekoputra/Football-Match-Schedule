package com.example.rizkyekoputra.footballmatchschedule.Utils

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsKtTest {

    @Test
    fun formatDateToString() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wednesday, 28 February 2018", formatDateToString(date))
    }

    @Test
    fun replaceColonWithNewLine() {
        val string = "first string; second string; and third string; "
        assertEquals("first string\nsecond string\nand third string", replaceColonWithNewLine(string))
    }
}