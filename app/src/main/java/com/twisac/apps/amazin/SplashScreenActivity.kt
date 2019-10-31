package com.twisac.apps.amazin

/**
 * Created by elvin Ambasa on 2/23/17.
 *
 */

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.twisac.apps.amazin.component.SessionManager
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val animation = AnimationUtils.loadAnimation(this,R.anim.move_up)
        iv_logo.animation = animation

        val animation2 = AnimationUtils.loadAnimation(this,R.anim.blink)
        tv_title.animation = animation2

        object : CountDownTimer(COUNTEND.toLong(), COUNSTART.toLong()) {
            /**
             * This method simply is called when the millisecond counter finishes to run.
             *
             * @param millisUntilFinished trigger when the milliseconds are reached.
             */
            override fun onTick(millisUntilFinished: Long) {
              //  tv_title.text = "Time App"
            }

            /**
             * This method is triggered If an activity is paused or stopped, the system can drop the activity from memory by either asking it to finish, or simply killing its process. When it is displayed again to the user, it must be completely restarted and restored to its previous state.
             *
             */
            override fun onFinish() {

                startActvity()
            }
        }.start()

        //  }
    }

    /**
     * Called when the system is about to start resuming a previous activity.
     * This is typically used to commit unsaved changes to persistent data, stop animations and other things that may be consuming CPU, etc.
     * Implementations of this method must be very quick because the next activity will not be resumed until this method returns.
     */
    override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
        finish()
    }

    /**
     * The Activity can then start intent of LoginActivity or SplashActivity.
     * if Session is logged in it skips intro
     */
    private fun startActvity() {
        //skipping intro if true
        val sessionManager = SessionManager(this@SplashScreenActivity)
        if (sessionManager.isLoggedIn) {

            val intent = Intent(this@SplashScreenActivity, QuizActivity::class.java)
            startActivity(intent)
        } else {

            val intent2 = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
            startActivity(intent2)
        }

    }

    companion object {
        private val COUNTEND = 5000
        private val COUNSTART = 1000
    }

}