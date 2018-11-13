package com.example.rizkyekoputra.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer")
        var playerId: String? = null,

        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strDescriptionEN")
        var playerDescription: String? = null,

        @SerializedName("strPosition")
        var playerPosition: String? = null,

        @SerializedName("strHeight")
        var playerHeight: String? = null,

        @SerializedName("strWeight")
        var playerWeight: String? = null,

        @SerializedName("strThumb")
        var playerThumb: String? = null,

        @SerializedName("strCutout")
        var playerCutout: String? = null,

        @SerializedName("strFanart1")
        var playerFanart: String? = null
)