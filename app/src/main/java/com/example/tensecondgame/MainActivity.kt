package com.example.tensecondgame

import FadeSlideAnim
import FullScreenHelper
import android.app.ActivityOptions
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import pl.droidsonroids.gif.GifImageView

class MainActivity : AppCompatActivity() {
    private lateinit var fullScreenHelper: FullScreenHelper
    private lateinit var fadeSlide: FadeSlideAnim
    private lateinit var rulesDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fullScreenHelper = FullScreenHelper(this)
        fullScreenHelper.enterFullScreen()
        fadeSlide = FadeSlideAnim(this)


        val zoom_animation = AnimationUtils.loadAnimation(this, R.anim.zoom)
        val slide_down_animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        val slide_up_animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)


        val title_text = findViewById<GifImageView>(R.id.title_txt)
        val welcome_text = findViewById<ImageView>(R.id.welcome_txt)
        val jet_img = findViewById<ImageView>(R.id.jet_img)
        val play_btn = findViewById<ImageButton>(R.id.startButton)
        val exit_btn = findViewById<ImageButton>(R.id.exitButton)


        fadeSlide.applyAnimation(findViewById(R.id.startButton), R.anim.fade_in, R.anim.slide_up2, 1000)
        fadeSlide.applyAnimation(findViewById(R.id.exitButton), R.anim.fade_in, R.anim.slide_up2, 2000)
        fadeSlide.applyAnimation(findViewById(R.id.htp_button), R.anim.fade_in, R.anim.slide_up2, 1500)
        welcome_text.startAnimation(slide_down_animation)
        title_text.startAnimation(zoom_animation)
        jet_img.startAnimation(slide_up_animation)



        play_btn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_up, R.anim.slide_up)
            startActivity(intent, options.toBundle())
        }
        exit_btn.setOnClickListener {
            finish()
        }

        val htpButton = findViewById<ImageButton>(R.id.htp_button)

        rulesDialog = Dialog(this)
        rulesDialog.setContentView(R.layout.rules_layout)
        val closeButton = rulesDialog.findViewById<ImageButton>(R.id.closeButton) // You need to add a "closeButton" in the rules_layout.xml

        // Set the background of the dialog's window to transparent
        rulesDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        htpButton.setOnClickListener {
            rulesDialog.show()
        }

        closeButton.setOnClickListener {
            rulesDialog.dismiss()
        }
    }
    }

