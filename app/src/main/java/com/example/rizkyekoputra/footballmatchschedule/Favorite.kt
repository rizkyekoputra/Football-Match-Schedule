package com.example.rizkyekoputra.footballmatchschedule

class Favorite(val id: Long?, val matchId: String?, val matchName: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_NAME: String = "MATCH_NAME"
    }
}