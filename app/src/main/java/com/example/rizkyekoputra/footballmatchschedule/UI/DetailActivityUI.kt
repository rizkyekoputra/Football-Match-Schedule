package com.example.rizkyekoputra.footballmatchschedule.UI

import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.rizkyekoputra.footballmatchschedule.MatchDetailActivity
import com.example.rizkyekoputra.footballmatchschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailActivityUI : AnkoComponent<MatchDetailActivity> {
    override fun createView(ui: AnkoContext<MatchDetailActivity>) = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            backgroundColor = Color.WHITE

            owner.swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                scrollView {
                    verticalLayout {
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
                                verticalLayout {
                                    owner.matchDateTv = textView {
                                    }.lparams {
                                        gravity = Gravity.CENTER
                                        bottomMargin = dip(10)
                                    }

                                    linearLayout {
                                        orientation = LinearLayout.HORIZONTAL

                                        verticalLayout {
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

                                        verticalLayout {
                                            owner.homeScoreTv = textView {
                                                gravity = Gravity.CENTER
                                                textSize = 40f
                                            }.lparams(matchParent, wrapContent)
                                        }.lparams(width = dip(0), height = wrapContent) {
                                            gravity = Gravity.CENTER
                                            weight = 0.5F
                                        }

                                        verticalLayout {
                                            textView("VS") {
                                                gravity = Gravity.CENTER
                                                textSize = 18f
                                            }.lparams(matchParent, wrapContent)
                                        }.lparams(width = dip(0), height = wrapContent) {
                                            gravity = Gravity.CENTER
                                            weight = 1F
                                        }

                                        verticalLayout {
                                            owner.awayScoreTv = textView {
                                                gravity = Gravity.CENTER
                                                textSize = 40f
                                            }.lparams(matchParent, wrapContent)
                                        }.lparams(width = dip(0), height = wrapContent) {
                                            gravity = Gravity.CENTER
                                            weight = 0.5F
                                        }

                                        verticalLayout {
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

                            verticalLayout {
                                owner.homeGoalsTv = textView {
                                    gravity = Gravity.LEFT
                                }.lparams(matchParent, wrapContent) {
                                    topMargin = dip(4)
                                }
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 3F
                            }

                            verticalLayout {
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

                            verticalLayout {
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

                            verticalLayout {
                                owner.homeShotsTv = textView {
                                    gravity = Gravity.LEFT
                                }.lparams(matchParent, wrapContent) {
                                    topMargin = dip(4)
                                }
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 3F
                            }

                            verticalLayout {
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

                            verticalLayout {
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

                            verticalLayout {
                                owner.homeGoalKeeperTv = textView {
                                    gravity = Gravity.LEFT
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 3F
                            }

                            verticalLayout {
                                textView("Goal Keeper") {
                                    gravity = Gravity.CENTER
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 1.5F
                                leftMargin = dip(10)
                                rightMargin = dip(10)
                            }

                            verticalLayout {
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

                            verticalLayout {
                                owner.homeDefenseTv = textView {
                                    gravity = Gravity.LEFT
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 3F
                            }

                            verticalLayout {
                                textView("Defense") {
                                    gravity = Gravity.CENTER
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 1.5F
                                leftMargin = dip(10)
                                rightMargin = dip(10)
                            }

                            verticalLayout {
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

                            verticalLayout {
                                owner.homeMidfieldTv = textView {
                                    gravity = Gravity.LEFT
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 3F
                            }

                            verticalLayout {
                                textView("Midfield") {
                                    gravity = Gravity.CENTER
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 1.5F
                                leftMargin = dip(10)
                                rightMargin = dip(10)
                            }

                            verticalLayout {
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

                            verticalLayout {
                                owner.homeForwardTv = textView {
                                    gravity = Gravity.LEFT
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 3F
                            }

                            verticalLayout {
                                textView("Forward") {
                                    gravity = Gravity.CENTER
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 1.5F
                                leftMargin = dip(10)
                                rightMargin = dip(10)
                            }

                            verticalLayout {
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

                            verticalLayout {
                                owner.homeSubstitutesTv = textView {
                                    gravity = Gravity.LEFT
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 3F
                            }

                            verticalLayout {
                                textView("Substitutes") {
                                    gravity = Gravity.CENTER
                                }.lparams(matchParent, wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.TOP
                                weight = 1.5F
                                leftMargin = dip(10)
                                rightMargin = dip(10)
                            }

                            verticalLayout {
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
    }
}