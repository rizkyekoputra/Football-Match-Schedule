package com.example.rizkyekoputra.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.MatchDetailActivity
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.UI.EventUI
import com.example.rizkyekoputra.footballmatchschedule.Utils.formatDateToString
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MatchAdapter(private val events: List<Event>) : RecyclerView.Adapter<MatchAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position])
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvMatchDate: TextView = itemView.find(R.id.match_date)
        private val tvHomeTeamName: TextView = itemView.find(R.id.home_team_name)
        private val tvAwayTeamName: TextView = itemView.find(R.id.away_team_name)
        private val tvHomeScore: TextView = itemView.find(R.id.home_score)
        private val tvAwayScore: TextView = itemView.find(R.id.away_score)

        fun bindItem(event: Event) {
            tvMatchDate.text = event.dateEvent?.let { formatDateToString(it) }
            tvHomeTeamName.text = event.homeTeamName
            tvAwayTeamName.text = event.awayTeamName
            tvHomeScore.text = event.homeScore.let { it?.toString() ?: "" }
            tvAwayScore.text = event.awayScore.let { it?.toString() ?: "" }

            itemView.onClick {
                itemView.context.startActivity<MatchDetailActivity>("match" to event)
            }
        }
    }
}