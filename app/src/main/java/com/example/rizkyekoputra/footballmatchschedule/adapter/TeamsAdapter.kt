package com.example.rizkyekoputra.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.R.id.team_badge
import com.example.rizkyekoputra.footballmatchschedule.R.id.team_name
import com.example.rizkyekoputra.footballmatchschedule.UI.TeamUI
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamsAdapter (private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(team: Team, listener: (Team) -> Unit) {
        Picasso.get().load(team.teamBadge).into(teamBadge)
        teamName.text = team.teamName

        itemView.onClick { listener(team) }
    }
}