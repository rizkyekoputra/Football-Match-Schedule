package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.nestedScrollView

class TeamOverviewFragment : Fragment() {

    private lateinit var teamDescription: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        teamDescription.text = arguments?.getCharSequence("description")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    private fun createView(ui: AnkoContext<Context>): View = with(ui) {
        nestedScrollView {
            lparams(width = matchParent, height = wrapContent)
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(10)

                teamDescription = textView().lparams {
                    topMargin = dip(20)
                }
            }
        }
    }
}