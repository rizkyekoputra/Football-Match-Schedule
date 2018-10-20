package com.example.rizkyekoputra.footballmatchschedule

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class LastMatchAdapter(val events: List<Event>) : RecyclerView.Adapter<LastMatchAdapter.EventViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position])
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        lateinit var tvHomeTeamName: TextView
        lateinit var tvAwayTeamName: TextView

        fun bindItem(events: Event) {
            tvHomeTeamName = itemView.findViewById(R.id.home_team_name) as TextView
            tvAwayTeamName = itemView.findViewById(R.id.away_team_name) as TextView

            tvHomeTeamName.text = events.homeTeamName
            tvAwayTeamName.text = events.awayTeamName
        }

    }
}

class EventUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            textView {
                id = R.id.home_team_name
                textSize = 16f
            }.lparams{
                margin = dip(15)
            }

            textView {
                id = R.id.away_team_name
                textSize = 16f
            }.lparams{
                margin = dip(15)
            }

        }
    }

}

