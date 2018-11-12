package com.example.rizkyekoputra.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.UI.PlayerUI
import com.example.rizkyekoputra.footballmatchschedule.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }
}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerThumb: ImageView = view.find(R.id.player_thumb)
    private val playerName: TextView = view.find(R.id.player_name)
    private val playerPosition: TextView = view.find(R.id.player_position)

    fun bindItem(player: Player, listener: (Player) -> Unit) {
        Picasso.get().load(player.playerCutout).placeholder(R.drawable.photo_placeholder).into(playerThumb)
        playerName.text = player.playerName
        playerPosition.text = player.playerPosition

        itemView.onClick { listener(player) }
    }
}