package com.example.rizkyekoputra.footballmatchschedule

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge")
        var teamBadgeUrl: String? = null
)