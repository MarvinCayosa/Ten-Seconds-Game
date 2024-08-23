package com.example.tensecondgame

import FullScreenHelper
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoadingScreen : AppCompatActivity() {
    private lateinit var fullScreenHelper: FullScreenHelper
    private val typingHandler = Handler()
    private val typingDelay = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenHelper = FullScreenHelper(this)
        fullScreenHelper.enterFullScreen()
        setContentView(R.layout.activity_loading_screen)



        val jetImageView = findViewById<ImageView>(R.id.jet)
        val letter_text = findViewById<TextView>(R.id.loading_txt)
        val letter_content = getString(R.string.loading)


        // Create a Handler associated with the main Looper
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an animation to move the jetImageView
            val animation = TranslateAnimation(0f, 0f, 0f, -1500f) // Adjust these values as needed
            animation.duration = 500 // Adjust the duration as needed (1 second in this example)
            animation.fillAfter = true
            jetImageView.startAnimation(animation)

            // Delay the intent by 2 seconds (2000 milliseconds)
            Handler(Looper.getMainLooper()).postDelayed({
                // Create an Intent to start the new activity
                val intent = Intent(this, MainActivity::class.java)
                val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_up, R.anim.slide_up)
                startActivity(intent, options.toBundle())
                // Finish the current activity so the user can't go back to it
                finish()
            }, 500)
        }, 3000) // 1000 milliseconds (1 second)

        fun typingAnim(textView: TextView, text: String) {
            var textIndex = 0

            fun animateText() {
                if (textIndex < text.length) {
                    textView.text = text.substring(0, textIndex + 1)
                    textIndex++
                    typingHandler.postDelayed({ animateText() }, typingDelay.toLong())
                }
            }

            animateText()
        }
        typingAnim(letter_text, letter_content)
    }
}

