package com.example.rizkyekoputra.footballmatchschedule.model

class FavoriteMatch(val id: Long?,
                    val matchId: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
    }
}