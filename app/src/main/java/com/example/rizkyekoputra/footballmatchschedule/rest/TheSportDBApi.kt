package com.example.rizkyekoputra.footballmatchschedule.rest

import com.example.rizkyekoputra.footballmatchschedule.BuildConfig

object TheSportDBApi {
    fun getMatch(league: String?, type: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/$type.php?id=" + league
    }

    fun getTeamById(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/${BuildConfig.LOOKUP_TEAM_KEY}?id=" + id
    }

    fun getEventById(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/${BuildConfig.LOOKUP_EVENT_KEY}?id=" + id
    }
}