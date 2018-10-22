package com.example.rizkyekoputra.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge")
        var teamBadgeUrl: String? = null
)