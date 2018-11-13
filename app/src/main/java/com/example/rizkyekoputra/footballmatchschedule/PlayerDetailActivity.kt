package com.example.rizkyekoputra.footballmatchschedule

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.rizkyekoputra.footballmatchschedule.Utils.invisible
import com.example.rizkyekoputra.footballmatchschedule.Utils.visible
import com.example.rizkyekoputra.footballmatchschedule.model.Player
import com.example.rizkyekoputra.footballmatchschedule.presenter.PlayerDetailPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {
    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var player: Player
    private lateinit var progressBar: ProgressBar

    private lateinit var playerBanner: ImageView
    private lateinit var playerHeight: TextView
    private lateinit var playerWeight: TextView
    private lateinit var playerPosition: TextView
    private lateinit var playerDescription: TextView

    private lateinit var id: String
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        supportActionBar?.title = name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            scrollView {
                isVerticalScrollBarEnabled = false
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        playerBanner =  imageView {}.lparams(height = dip(203), width = matchParent)

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            verticalLayout {
                                textView {
                                    gravity = Gravity.CENTER
                                    textSize = 16f
                                    text = "Weight (Kg)"
                                }.lparams(width = matchParent, height = wrapContent) {
                                    topMargin = dip(5)
                                    bottomMargin = dip(5)
                                }

                                playerWeight = textView {
                                    gravity = Gravity.CENTER
                                    textSize = 46f
                                }.lparams(width = matchParent, height = wrapContent)
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.CENTER
                                weight = 1F
                                leftMargin = dip(5)
                            }

                            verticalLayout {
                                textView {
                                    gravity = Gravity.CENTER
                                    textSize = 16f
                                    text = "Height (m)"
                                }.lparams(width = matchParent, height = wrapContent) {
                                    topMargin = dip(5)
                                    bottomMargin = dip(5)
                                }

                                playerHeight = textView {
                                    gravity = Gravity.CENTER
                                    textSize = 46f
                                }.lparams(width = matchParent, height = wrapContent) {
                                }
                            }.lparams(width = dip(0), height = wrapContent) {
                                gravity = Gravity.CENTER
                                weight = 1F
                                rightMargin = dip(5)
                            }
                        }.lparams(width = matchParent, height = wrapContent)

                        playerPosition = textView{
                            textSize = 17f
                        }.lparams{
                            topMargin = dip(10)
                        }

                        view{
                            backgroundColor = Color.GRAY
                        }.lparams(matchParent, dip(0.5F)) {
                            topMargin = dip(5)
                        }

                        playerDescription = textView().lparams{
                            margin = dip(15)
                        }
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerInParent()
                    }
                }
            }
        }

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailPresenter(this, request, gson)
        presenter.getPlayerDetail(id)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerDetail(data: Player) {
        Picasso.get().load(data.playerFanart).placeholder(R.drawable.default_placeholder).into(playerBanner)
        playerHeight.text = data.playerHeight
        playerWeight.text = data.playerWeight
        playerPosition.text = data.playerPosition
        playerDescription.text = data.playerDescription
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
