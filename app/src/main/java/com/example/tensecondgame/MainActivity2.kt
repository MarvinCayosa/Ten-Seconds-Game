package com.example.tensecondgame

import FullScreenHelper
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

private var timer: Timer? = null
private var isTimerRunning = false
private var milliseconds = 0
private var seconds = 0
private lateinit var startStopButton: ImageButton
private lateinit var timerTextView: TextView
private lateinit var button_txt: TextView
private lateinit var fullScreenHelper: FullScreenHelper
private val leaderboard: MutableList<Rank> = mutableListOf()


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fullScreenHelper = FullScreenHelper(this)
        fullScreenHelper.enterFullScreen()

        startStopButton = findViewById(R.id.btn_start_stop)
        timerTextView = findViewById(R.id.txt_timer)
        button_txt = findViewById(R.id.button_txt)

        val leaderboardButton = findViewById<ImageButton>(R.id.btn_leaderboard)

        leaderboardButton.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putParcelableArrayListExtra("leaderboard", ArrayList(leaderboard))
            startActivity(intent)
        }

        startStopButton.setOnClickListener {
            if (isTimerRunning) {
                stopTimer()
                timerTextView.visibility = View.VISIBLE

            } else {
                if (seconds != 0 || milliseconds != 0) {
                    resetTimer()
                    timerTextView.visibility = View.VISIBLE
                } else {
                    startTimer()
                    timerTextView.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        button_txt.text = "Stop"
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                milliseconds++
                if (milliseconds == 1000) {
                    milliseconds = 0
                    seconds++
                }
                updateTimerText()
            }
        }, 0, 1)
    }

    private fun stopTimer() {
        isTimerRunning = false
        button_txt.text = "Reset"
        timer?.cancel()
        val message = findViewById<TextView>(R.id.txt_message)


        val stoppedTime = (seconds * 1000 + milliseconds).toLong()
        if (stoppedTime in 9500..9999 || stoppedTime in 10001..10500) {
            Handler(Looper.getMainLooper()).post {
                message.setText("That was close!")
                message.visibility = View.VISIBLE

            }
        } else if (stoppedTime <= 9499 || stoppedTime >= 10501) {
            Handler(Looper.getMainLooper()).post {
                message.setText("Try again!")
                message.visibility = View.VISIBLE

            }
        } else if (stoppedTime == 10000L) {
            Handler(Looper.getMainLooper()).post {
                message.setText("JACKPOT!")
                message.visibility = View.VISIBLE

            }
        }
        if (stoppedTime <= 10000L) {
            leaderboard.add(Rank(stoppedTime))
            leaderboard.sortBy { Math.abs(it.time - 10000L) }
            leaderboard.removeAll { it.time > 10000L }
        }
    }

    private fun resetTimer() {
        timer?.cancel()
        milliseconds = 0
        seconds = 0
        updateTimerText()
        isTimerRunning = false
        button_txt.text = "Start"

        val message = findViewById<TextView>(R.id.txt_message)
        message.visibility = View.GONE
    }

    private fun updateTimerText() {
        val timeString = String.format("%02d:%03d", seconds, milliseconds)
        Handler(Looper.getMainLooper()).post {
            timerTextView.text = timeString
        }
    }
}