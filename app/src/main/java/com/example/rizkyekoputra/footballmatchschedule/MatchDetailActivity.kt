package com.example.rizkyekoputra.footballmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.rizkyekoputra.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.rizkyekoputra.footballmatchschedule.R.id.add_to_favorite
import com.example.rizkyekoputra.footballmatchschedule.R.menu.detail_menu
import com.example.rizkyekoputra.footballmatchschedule.UI.DetailActivityUI
import com.example.rizkyekoputra.footballmatchschedule.Utils.formatDateToString
import com.example.rizkyekoputra.footballmatchschedule.Utils.replaceColonWithNewLine
import com.example.rizkyekoputra.footballmatchschedule.View.DetailView
import com.example.rizkyekoputra.footballmatchschedule.helper.database
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.model.FavoriteMatch
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.example.rizkyekoputra.footballmatchschedule.presenter.DetailPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.dip
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class MatchDetailActivity : AppCompatActivity(), DetailView {

    lateinit var matchDateTv: TextView
    lateinit var homeBadgeIv: ImageView
    lateinit var awayBadgeIv: ImageView
    lateinit var homeScoreTv: TextView
    lateinit var awayScoreTv: TextView
    lateinit var homeNameTv: TextView
    lateinit var awayNameTv: TextView
    lateinit var homeGoalsTv: TextView
    lateinit var awayGoalsTv: TextView
    lateinit var homeShotsTv: TextView
    lateinit var awayShotsTv: TextView
    lateinit var homeGoalKeeperTv: TextView
    lateinit var awayGoalKeeperTv: TextView
    lateinit var homeDefenseTv: TextView
    lateinit var awayDefenseTv: TextView
    lateinit var homeMidfieldTv: TextView
    lateinit var awayMidfieldTv: TextView
    lateinit var homeForwardTv: TextView
    lateinit var awayForwardTv: TextView
    lateinit var homeSubstitutesTv: TextView
    lateinit var awaySubstitutesTv: TextView
    lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var event: Event
    private lateinit var matchId: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun displayHomeTeamBadge(team: Team) {
        Picasso.get()
                .load(team.teamBadge)
                .placeholder(R.drawable.default_placeholder)
                .resize(dip(90), dip(90))
                .into(homeBadgeIv)
    }

    override fun displayAwayTeamBadge(team: Team) {
        Picasso.get()
                .load(team.teamBadge)
                .placeholder(R.drawable.default_placeholder)
                .into(awayBadgeIv)
    }

    private lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailActivityUI().setContentView(this)

        event = intent.getParcelableExtra("match")
        matchId = event.eventId.orEmpty()

        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        mPresenter = DetailPresenter(this, request, gson)
        mPresenter.getHomeTeamBadge(event.homeTeamId)
        mPresenter.getAwayTeamBadge(event.awayTeamId)
        initData(event)
        supportActionBar?.title = getString(R.string.match_detail)

        swipeRefresh.onRefresh {
            mPresenter.getHomeTeamBadge(event.homeTeamId)
            mPresenter.getAwayTeamBadge(event.awayTeamId)
            swipeRefresh.isRefreshing = false
        }
    }

    private fun initData(event: Event) {
        matchDateTv.text = event.dateEvent?.let { formatDateToString(it) }
        homeNameTv.text = event.homeTeamName
        homeScoreTv.text = event.homeScore.let { it?.toString() ?: "" }
        awayNameTv.text = event.awayTeamName
        awayScoreTv.text = event.awayScore.let { it?.toString() ?: "" }
        homeGoalsTv.text = event.homeGoalDetails?.let { replaceColonWithNewLine(it) }
        awayGoalsTv.text = event.awayGoalDetails?.let { replaceColonWithNewLine(it) }
        homeShotsTv.text = event.homeShots.let { it?.toString() ?: "" }
        awayShotsTv.text = event.awayShots.let { it?.toString() ?: "" }
        homeGoalKeeperTv.text = event.homeLineupGoalkeeper?.let { replaceColonWithNewLine(it) }
        awayGoalKeeperTv.text = event.awayLineupGoalkeeper?.let { replaceColonWithNewLine(it) }
        homeDefenseTv.text = event.homeLineupDefense?.let { replaceColonWithNewLine(it) }
        awayDefenseTv.text = event.awayLineupDefense?.let { replaceColonWithNewLine(it) }
        homeMidfieldTv.text = event.homeLineupMidfield?.let { replaceColonWithNewLine(it) }
        awayMidfieldTv.text = event.awayLineupMidfield?.let { replaceColonWithNewLine(it) }
        homeForwardTv.text = event.homeLineupForward?.let { replaceColonWithNewLine(it) }
        awayForwardTv.text = event.awayLineupForward?.let { replaceColonWithNewLine(it) }
        homeSubstitutesTv.text = event.homeLineupSubstitutes?.let { replaceColonWithNewLine(it) }
        awaySubstitutesTv.text = event.awayLineupSubstitutes?.let { replaceColonWithNewLine(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.MATCH_ID to event.eventId)
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(MATCH_ID = {id})",
                        "id" to matchId)
            }
            swipeRefresh.snackbar("Removed from favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(MATCH_ID = {id})",
                            "id" to matchId)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}