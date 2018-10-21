package com.example.rizkyekoputra.footballmatchschedule

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Event(
        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("idSoccerXML")
        var soccerXMLId: String? = null,

        @SerializedName("strEvent")
        var eventName: String? = null,

        @SerializedName("idLeague")
        var leagueId: String? = null,

        @SerializedName("strLeague")
        var leagueName: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeamName: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeamName: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: Int? = null,

        @SerializedName("intAwayScore")
        var awayScore: Int? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String? = null,

        @SerializedName("strHomeRedCards")
        var homeRedCards: String? = null,

        @SerializedName("strHomeYellowCards")
        var homeYellowCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var homeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var homeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var homeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var homeLineupSubstitutes: String? = null,

        @SerializedName("strHomeFormation")
        var homeFormation: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String? = null,

        @SerializedName("strAwayRedCards")
        var awayRedCards: String? = null,

        @SerializedName("strAwayYellowCards")
        var awayYellowCards: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        var awayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var awayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        var awayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var awayLineupSubstitutes: String? = null,

        @SerializedName("strAwayFormation")
        var awayFormation: String? = null,

        @SerializedName("intHomeShots")
        var homeShots: Int? = null,

        @SerializedName("intAwayShots")
        var awayShots: Int? = null,

        @SerializedName("dateEvent")
        var dateEvent: Date? = null,

        @SerializedName("strDate")
        var dateDisplay: String? = null,

        @SerializedName("idHomeTeam")
        var homeTeamId: String? = null,

        @SerializedName("idAwayTeam")
        var awayTeamId: String? = null
): Parcelable