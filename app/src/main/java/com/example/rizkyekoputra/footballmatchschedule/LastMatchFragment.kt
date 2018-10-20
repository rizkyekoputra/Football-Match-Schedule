package com.example.rizkyekoputra.footballmatchschedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.rizkyekoputra.footballmatchschedule.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
        = LastMatchFragmentUI<Fragment>().createView(AnkoContext.create(ctx, this))
}

class LastMatchFragmentUI<T>: AnkoComponent<T> {
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            spinner = spinner ()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }

    }
}
