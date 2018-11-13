package com.example.rizkyekoputra.footballmatchschedule.View

import com.example.rizkyekoputra.footballmatchschedule.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}