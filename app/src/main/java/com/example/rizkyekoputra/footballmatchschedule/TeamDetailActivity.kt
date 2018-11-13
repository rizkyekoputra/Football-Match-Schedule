package com.example.rizkyekoputra.footballmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.rizkyekoputra.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.rizkyekoputra.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.rizkyekoputra.footballmatchschedule.R.id.add_to_favorite
import com.example.rizkyekoputra.footballmatchschedule.R.menu.detail_menu
import com.example.rizkyekoputra.footballmatchschedule.Utils.invisible
import com.example.rizkyekoputra.footballmatchschedule.Utils.visible
import com.example.rizkyekoputra.footballmatchschedule.View.TeamDetailView
import com.example.rizkyekoputra.footballmatchschedule.adapter.ViewPagerAdapter
import com.example.rizkyekoputra.footballmatchschedule.fragment.PlayersFragment
import com.example.rizkyekoputra.footballmatchschedule.fragment.TeamOverviewFragment
import com.example.rizkyekoputra.footballmatchschedule.helper.database
import com.example.rizkyekoputra.footballmatchschedule.model.FavoriteTeam
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.example.rizkyekoputra.footballmatchschedule.presenter.TeamDetailPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var teamId: String
    private lateinit var teamDescription: String
    private lateinit var teamName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)

        val intent = intent
        teamId = intent.getStringExtra("id")
        teamDescription = intent.getStringExtra("description")
        teamName = intent.getStringExtra("name")
        supportActionBar?.title = teamName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F

        collapsing_toolbar?.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
        collapsing_toolbar?.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white))

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(teamId)

        val bundle = Bundle()
        bundle.putCharSequence("id", teamId)
        bundle.putCharSequence("description", teamDescription)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            populateFragment(TeamOverviewFragment().apply { arguments = bundle }, getString(R.string.overview))
            populateFragment(PlayersFragment().apply { arguments = bundle }, getString(R.string.player))
        }
        viewpager_team_detail.adapter = fragmentAdapter

        tabs_team_detail.setupWithViewPager(viewpager_team_detail)

    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {teamId})",
                            "teamId" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(team_detail_image)
        team_detail_name.text = data[0].teamName
        team_detail_year.text = data[0].teamFormedYear
        team_detail_stadium.text = data[0].teamStadium
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
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to teams.teamId)
            }
            Snackbar.make(tabs_team_detail, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(tabs_team_detail, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {teamId})",
                        "teamId" to teamId)
            }
            Snackbar.make(tabs_team_detail, "Removed from favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(tabs_team_detail, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
