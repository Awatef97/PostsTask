package iti.intake40.khaznatask.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import iti.intake40.khaznatask.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.animated_splash
        )
        imageView.startAnimation(animation)

        val handler= Handler()
        handler.postDelayed(({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
             finish()
        }), 4000)

    }
}