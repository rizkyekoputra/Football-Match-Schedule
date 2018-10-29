package com.example.rizkyekoputra.footballmatchschedule

class Favorite(val id: Long?,
               val matchId: String?,
               val matchDate: String?,
               val homeTeamName: String?,
               val awayTeamName: String?,
               val homeScore: String?,
               val awayScore: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "HOME_AWAY_SCORE"
    }
}