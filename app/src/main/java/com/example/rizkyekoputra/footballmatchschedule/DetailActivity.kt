package com.example.rizkyekoputra.footballmatchschedule

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.Utils.DateHelper
import com.example.rizkyekoputra.footballmatchschedule.Utils.StringHelper
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.example.rizkyekoputra.footballmatchschedule.presenter.DetailPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
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

    override fun displayHomeTeamBadge(team: Team) {
        Picasso.get()
                .load(team.teamBadgeUrl)
                .placeholder(R.drawable.default_placeholder)
                .resize(dip(90), dip(90))
                .into(homeBadgeIv)
    }

    override fun displayAwayTeamBadge(team: Team) {
        Picasso.get()
                .load(team.teamBadgeUrl)
                .placeholder(R.drawable.default_placeholder)
                .into(awayBadgeIv)
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
        supportActionBar?.title = getString(R.string.match_detail)
    }

    private fun initData(event: Event) {
        matchDateTv.text = event.dateEvent?.let { DateHelper.formatDateToString(it) }
        homeNameTv.text = event.homeTeamName
        homeScoreTv.text = event.homeScore.let { it?.toString() ?: "" }
        awayNameTv.text = event.awayTeamName
        awayScoreTv.text = event.awayScore.let { it?.toString() ?: "" }
        homeGoalsTv.text = event.homeGoalDetails?.let { StringHelper.replaceColonWithNewLine(it) }
        awayGoalsTv.text = event.awayGoalDetails?.let { StringHelper.replaceColonWithNewLine(it) }
        homeShotsTv.text = event.homeShots.let { it?.toString() ?: "" }
        awayShotsTv.text = event.awayShots.let { it?.toString() ?: "" }
        homeGoalKeeperTv.text = event.homeLineupGoalkeeper?.let { StringHelper.replaceColonWithNewLine(it) }
        awayGoalKeeperTv.text = event.awayLineupGoalkeeper?.let { StringHelper.replaceColonWithNewLine(it) }
        homeDefenseTv.text = event.homeLineupDefense?.let { StringHelper.replaceColonWithNewLine(it) }
        awayDefenseTv.text = event.awayLineupDefense?.let { StringHelper.replaceColonWithNewLine(it) }
        homeMidfieldTv.text = event.homeLineupMidfield?.let { StringHelper.replaceColonWithNewLine(it) }
        awayMidfieldTv.text = event.awayLineupMidfield?.let { StringHelper.replaceColonWithNewLine(it) }
        homeForwardTv.text = event.homeLineupForward?.let { StringHelper.replaceColonWithNewLine(it) }
        awayForwardTv.text = event.awayLineupForward?.let { StringHelper.replaceColonWithNewLine(it) }
        homeSubstitutesTv.text = event.homeLineupSubstitutes?.let { StringHelper.replaceColonWithNewLine(it) }
        awaySubstitutesTv.text = event.awayLineupSubstitutes?.let { StringHelper.replaceColonWithNewLine(it) }
    }
}

class DetailActivityUI : AnkoComponent<DetailActivity> {
    override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
        scrollView {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(8)

                cardView {
                    layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT).apply {
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                        topMargin = dip(5)
                        bottomMargin = dip(5)

                    }
                    backgroundColor = Color.WHITE
                    radius = dip(8).toFloat()

                    relativeLayout {
                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            owner.matchDateTv = textView {
                            }.lparams {
                                gravity = Gravity.CENTER
                                bottomMargin = dip(10)
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
                                        textSize = 20f
                                    }.lparams(matchParent, wrapContent) {
                                        topMargin = dip(4)
                                    }
                                }.lparams(width = dip(0), height = wrapContent) {
                                    weight = 3F
                                }

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    owner.homeScoreTv = textView {
                                        gravity = Gravity.CENTER
                                        textSize = 40f
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
                                        textSize = 40f
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
                                        textSize = 20f
                                    }.lparams(matchParent, wrapContent) {
                                        topMargin = dip(4)
                                    }
                                }.lparams(width = dip(0), height = wrapContent) {
                                    weight = 3F
                                }
                            }.lparams(matchParent, wrapContent) {
                                bottomMargin = dip(10)
                            }
                        }.lparams(matchParent, wrapContent)
                    }
                }.lparams(matchParent, wrapContent)

                view {
                    backgroundColor = Color.LTGRAY
                }.lparams(matchParent, dip(1)) {
                    topMargin = dip(10)
                    bottomMargin = dip(10)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.homeGoalsTv = textView {
                            gravity = Gravity.LEFT
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(4)
                        }
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView("Goals") {
                            gravity = Gravity.CENTER
                            textSize = 18f
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 1.5F
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.awayGoalsTv = textView {
                            gravity = Gravity.RIGHT
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(4)
                        }
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(20)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.homeShotsTv = textView {
                            gravity = Gravity.LEFT
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(4)
                        }
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView("Shots") {
                            gravity = Gravity.CENTER
                            textSize = 18f
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 1.5F
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.awayShotsTv = textView {
                            gravity = Gravity.RIGHT
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(4)
                        }
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(10)
                }

                view {
                    backgroundColor = Color.LTGRAY
                }.lparams(matchParent, dip(1)) {
                    bottomMargin = dip(10)
                }

                textView("Lineups") {
                    textSize = 18f
                }.lparams {
                    gravity = Gravity.CENTER
                    bottomMargin = dip(20)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.homeGoalKeeperTv = textView {
                            gravity = Gravity.LEFT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView("Goal Keeper") {
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 1.5F
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.awayGoalKeeperTv = textView {
                            gravity = Gravity.RIGHT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(15)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.homeDefenseTv = textView {
                            gravity = Gravity.LEFT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView("Defense") {
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 1.5F
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.awayDefenseTv = textView {
                            gravity = Gravity.RIGHT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(15)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.homeMidfieldTv = textView {
                            gravity = Gravity.LEFT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView("Midfield") {
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 1.5F
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.awayMidfieldTv = textView {
                            gravity = Gravity.RIGHT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(15)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.homeForwardTv = textView {
                            gravity = Gravity.LEFT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView("Forward") {
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 1.5F
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.awayForwardTv = textView {
                            gravity = Gravity.RIGHT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(15)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.homeSubstitutesTv = textView {
                            gravity = Gravity.LEFT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        textView("Substitutes") {
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 1.5F
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        owner.awaySubstitutesTv = textView {
                            gravity = Gravity.RIGHT
                        }.lparams(matchParent, wrapContent)
                    }.lparams(width = dip(0), height = wrapContent) {
                        gravity = Gravity.TOP
                        weight = 3F
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(15)
                }

            }.lparams(matchParent, wrapContent)
        }
    }
}