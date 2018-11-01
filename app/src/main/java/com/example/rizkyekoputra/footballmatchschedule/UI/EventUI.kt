package com.example.rizkyekoputra.footballmatchschedule.UI

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.rizkyekoputra.footballmatchschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class EventUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        frameLayout {
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
                    layoutParams = RelativeLayout.LayoutParams(matchParent, wrapContent)
                    padding = dip(10)

                    verticalLayout {
                        textView {
                            id = R.id.match_date
                            gravity = Gravity.CENTER
                            textSize = 12f
                        }.lparams {
                            width = matchParent
                            bottomMargin = dip(5)
                        }

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            verticalLayout {
                                textView {
                                    id = R.id.home_team_name
                                    gravity = Gravity.CENTER
                                    textSize = 16f
                                }.lparams(width = matchParent, height = wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.CENTER
                                weight = 3F
                                leftMargin = dip(5)
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                textView {
                                    id = R.id.home_score
                                    textSize = 28f
                                    gravity = Gravity.CENTER
                                }.lparams(width = matchParent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.CENTER
                                weight = 0.7F
                                leftMargin = dip(20)
                                rightMargin = dip(10)
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                textView("VS") {
                                    textSize = 18f
                                    gravity = Gravity.CENTER
                                }.lparams(width = matchParent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.CENTER
                                weight = 1F
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL

                                textView {
                                    id = R.id.away_score
                                    textSize = 28f
                                    gravity = Gravity.CENTER
                                }.lparams(width = matchParent, height = wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.CENTER
                                weight = 0.7F
                                rightMargin = dip(20)
                                leftMargin = dip(10)
                            }

                            verticalLayout {
                                textView {
                                    id = R.id.away_team_name
                                    gravity = Gravity.CENTER
                                    textSize = 16f
                                }.lparams(width = matchParent, height = wrapContent) {
                                }
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.CENTER
                                weight = 3F
                                rightMargin = dip(5)
                            }
                        }.lparams(width = matchParent, height = wrapContent)
                    }.lparams(width = matchParent, height = wrapContent)
                }
            }
        }
    }
}