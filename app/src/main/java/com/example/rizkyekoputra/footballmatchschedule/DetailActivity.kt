package com.example.rizkyekoputra.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class DetailActivity : AppCompatActivity(), DetailView {

    lateinit var matchDateTv: TextView
    lateinit var homeBadgeIv: ImageView
    lateinit var awayBadgeIv: ImageView
    lateinit var homeScoreTv: TextView
    lateinit var awayScoreTv: TextView
    lateinit var homeNameTv: TextView
    lateinit var awayNameTv: TextView

    override fun displayHomeTeamBadge(team: Team) {
        Picasso.get().load(team.teamBadgeUrl).into(homeBadgeIv)
    }

    override fun displayAwayTeamBadge(team: Team) {
        Picasso.get().load(team.teamBadgeUrl).into(awayBadgeIv)
    }

    private lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailActivityUI().setContentView(this)

        val request = ApiRepository()
        val gson = Gson()
        mPresenter = DetailPresenter(this, request, gson)

        val event = intent.getParcelableExtra<Event>("match")
        mPresenter.getHomeTeamBadge(event.homeTeamId)
        mPresenter.getAwayTeamBadge(event.awayTeamId)
        initData(event)
    }

    private fun initData(event: Event) {
        matchDateTv.text = event.dateEvent?.let { DateHelper.formatDateToString(it) }
        homeNameTv.text = event.homeTeamName
        homeScoreTv.text = event.homeScore.toString()
        awayNameTv.text = event.awayTeamName
        awayScoreTv.text = event.awayScore.toString()
    }
}

class DetailActivityUI : AnkoComponent<DetailActivity> {
    override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
        scrollView {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(8)

                cardView {
                    relativeLayout{
                        linearLayout{
                            orientation = LinearLayout.VERTICAL

                            owner.matchDateTv = textView {
                                gravity = Gravity.CENTER_HORIZONTAL
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    owner.homeBadgeIv = imageView {
                                    }.lparams(width = dip(90), height = dip(90)) {
                                        gravity = Gravity.CENTER
                                    }

                                    owner.homeNameTv = textView {
                                        gravity = Gravity.CENTER
                                    }.lparams(matchParent, wrapContent) {
                                        topMargin = dip(4)
                                    }
                                }.lparams(width = dip(0), height = wrapContent){
                                    weight = 3F
                                }

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    owner.homeScoreTv = textView {
                                        gravity = Gravity.CENTER
                                        textSize = 32f
                                    }.lparams(matchParent, wrapContent)
                                }.lparams(width = dip(0), height = wrapContent) {
                                    gravity = Gravity.CENTER
                                    weight = 0.5F
                                }

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    textView("VS") {
                                        gravity = Gravity.CENTER
                                        textSize = 18f
                                    }.lparams(matchParent, wrapContent)
                                }.lparams(width = dip(0), height = wrapContent) {
                                    gravity = Gravity.CENTER
                                    weight = 1F
                                }

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    owner.awayScoreTv = textView {
                                        gravity = Gravity.CENTER
                                        textSize = 32f
                                    }.lparams(matchParent, wrapContent)
                                }.lparams(width = dip(0), height = wrapContent) {
                                    gravity = Gravity.CENTER
                                    weight = 0.5F
                                }

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    owner.awayBadgeIv = imageView {
                                    }.lparams(width = dip(90), height = dip(90)) {
                                        gravity = Gravity.CENTER
                                    }

                                    owner.awayNameTv = textView {
                                        gravity = Gravity.CENTER
                                    }.lparams(matchParent, wrapContent) {
                                        topMargin = dip(4)
                                    }
                                }.lparams(width = dip(0), height = wrapContent) {
                                    weight = 3F
                                }
                            }.lparams(matchParent, wrapContent)
                        }.lparams(matchParent, wrapContent)
                    }
                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, wrapContent)
        }
    }
}