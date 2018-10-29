package com.example.rizkyekoputra.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.DetailActivity
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.Utils.DateHelper
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavoriteMatchesAdapter(private val favorite: List<Event>)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position])
    }

    override fun getItemCount(): Int = favorite.size
}

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val tvMatchDate: TextView = itemView.find(R.id.match_date)
    private val tvHomeTeamName: TextView = itemView.find(R.id.home_team_name)
    private val tvAwayTeamName: TextView = itemView.find(R.id.away_team_name)
    private val tvHomeScore: TextView = itemView.find(R.id.home_score)
    private val tvAwayScore: TextView = itemView.find(R.id.away_score)

    fun bindItem(favorite: Event) {
        tvMatchDate.text = favorite.dateEvent?.let { DateHelper.formatDateToString(it) }
        tvHomeTeamName.text = favorite.homeTeamName
        tvAwayTeamName.text = favorite.awayTeamName
        tvHomeScore.text = favorite.homeScore.let { it?.toString() ?: "" }
        tvAwayScore.text = favorite.awayScore.let { it?.toString() ?: "" }

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailActivity>("match" to favorite)
        }
    }
}