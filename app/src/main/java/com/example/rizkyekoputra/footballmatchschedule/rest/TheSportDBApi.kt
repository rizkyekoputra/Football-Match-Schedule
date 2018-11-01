package com.example.rizkyekoputra.footballmatchschedule.rest

import android.net.Uri
import com.example.rizkyekoputra.footballmatchschedule.BuildConfig

object TheSportDBApi {
    fun getMatch(league: String?, type: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("$type.php")
                .appendQueryParameter("id", league)
                .build()
                .toString()
    }

    fun getTeamById(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(BuildConfig.LOOKUP_TEAM_KEY)
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }

    fun getEventById(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(BuildConfig.LOOKUP_EVENT_KEY)
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }
}