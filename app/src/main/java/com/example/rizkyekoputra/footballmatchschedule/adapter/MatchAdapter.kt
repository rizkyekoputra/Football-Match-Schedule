package com.example.rizkyekoputra.footballmatchschedule.adapter

import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.MatchDetailActivity
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.UI.EventUI
import com.example.rizkyekoputra.footballmatchschedule.Utils.formatDateToString
import com.example.rizkyekoputra.footballmatchschedule.Utils.formatTimeToString
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

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
        private val tvMatchTime: TextView = itemView.find(R.id.match_time)
        private val tvHomeTeamName: TextView = itemView.find(R.id.home_team_name)
        private val tvAwayTeamName: TextView = itemView.find(R.id.away_team_name)
        private val tvHomeScore: TextView = itemView.find(R.id.home_score)
        private val tvAwayScore: TextView = itemView.find(R.id.away_score)
        private val ivNotification: ImageView = itemView.find(R.id.notif)

        fun bindItem(event: Event) {
            tvMatchDate.text = event.dateEvent?.let { formatDateToString(it) }
            tvMatchTime.text = event.timeDisplay?.let { formatTimeToString(it) }
            tvHomeTeamName.text = event.homeTeamName
            tvAwayTeamName.text = event.awayTeamName
            tvHomeScore.text = event.homeScore.let { it?.toString() ?: "" }
            tvAwayScore.text = event.awayScore.let { it?.toString() ?: "" }
            event.awayScore.let {
                if (it != null) ivNotification.visibility = View.INVISIBLE
            }

            itemView.onClick {
                itemView.context.startActivity<MatchDetailActivity>("match" to event)
            }

            ivNotification.onClick {
                val time = event.timeDisplay
                val fullTime = event.dateDisplay + " " + time
                val simpleDate = SimpleDateFormat("dd/MM/yy HH:mm:ssXXX", Locale.getDefault())
                val matchDateTime = simpleDate.parse(fullTime)

                val intent = Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, matchDateTime.time)
                        .putExtra(CalendarContract.Events.TITLE, event.eventName)
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                itemView.context.startActivity(intent)
            }
        }
    }
}