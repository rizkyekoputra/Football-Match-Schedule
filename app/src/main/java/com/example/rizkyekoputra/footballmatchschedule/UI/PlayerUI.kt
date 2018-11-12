package com.example.rizkyekoputra.footballmatchschedule.UI

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.rizkyekoputra.footballmatchschedule.R
import org.jetbrains.anko.*

class PlayerUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                verticalLayout {
                    imageView {
                        id = R.id.player_thumb
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }
                }.lparams(width = dip(50), height = wrapContent) {
                    gravity = Gravity.CENTER
                }

                verticalLayout {
                    textView {
                        id = R.id.player_name
                        textSize = 16f
                        gravity = Gravity.LEFT
                    }.lparams(width = wrapContent, height = wrapContent){
                        margin = dip(15)
                    }
                }.lparams(width = dip(0), height = wrapContent) {
                    gravity = Gravity.CENTER
                    weight = 3F
                }

                verticalLayout {
                    textView {
                        id = R.id.player_position
                        textSize = 16f
                        gravity = Gravity.RIGHT
                    }.lparams(width = matchParent, height = wrapContent){
                        margin = dip(15)
                    }
                }.lparams(width = dip(0), height = wrapContent) {
                    gravity = Gravity.CENTER
                    weight = 2F
                }
            }
        }
    }
}