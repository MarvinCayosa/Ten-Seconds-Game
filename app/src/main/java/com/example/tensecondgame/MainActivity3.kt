package com.example.tensecondgame
import FullScreenHelper
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

private lateinit var fullScreenHelper: FullScreenHelper
class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        fullScreenHelper = FullScreenHelper(this)
        fullScreenHelper.enterFullScreen()

        val leaderboard = intent.getParcelableArrayListExtra<Rank>("leaderboard")

        leaderboard?.sortBy { Math.abs(it.time - 10500L) }

        val leaderboardContainer: LinearLayout = findViewById(R.id.leaderboardContainer)

        val itemCount = minOf(leaderboard?.size ?: 0, 5)
        for (index in 0 until itemCount) {
            val item = leaderboard!![index]

            val rankText = when (index + 1) {
                1 -> "1st"
                2 -> "2nd"
                3 -> "3rd"
                else -> "${index + 1}th"
            }

            val timeInSecond = item.time / 1000
            val timeString = String.format("%02d.%03d", timeInSecond, item.time % 1000)

            val textView = TextView(this)
            textView.text = "$rankText: $timeString"
            textView.textSize = 30f
            val customFont = ResourcesCompat.getFont(this, R.font.mob)
            val textColor = ContextCompat.getColor(this, R.color.yellow) // Change 'your_color_resource' to your color resource
            textView.setTextColor(textColor)
            textView.typeface = customFont
            textView.gravity = Gravity.CENTER
            textView.setPadding(16, 16, 16, 16)
            leaderboardContainer.addView(textView)
        }
    }
}