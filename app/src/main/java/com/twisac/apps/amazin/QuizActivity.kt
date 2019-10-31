package com.twisac.apps.amazin

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.twisac.apps.amazin.component.AlertPopup
import com.twisac.apps.amazin.fragments.ChoiceFragment
import com.twisac.apps.amazin.fragments.GenderFragment
import com.twisac.apps.amazin.fragments.MeasurmentFragment
import com.twisac.apps.amazin.fragments.QuizFragment
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {
    private  var doubleBackToExitPressedOnce = false
    private var position =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)// get current flag
        // add LIGHT_STATUS_BAR to flag

        // get current flag
        // add LIGHT_STATUS_BAR to flag
        AlertPopup().statusBarTint(this@QuizActivity)
        val fragment1 = GenderFragment()
        val fragment2 = ChoiceFragment()
        val fragment3 =  QuizFragment()
        val fragment4 =  MeasurmentFragment()
        val fm = supportFragmentManager
        var active : Fragment = fragment1
        fm.beginTransaction().add(R.id.frame_layout, fragment4, "4").hide(fragment4).commit()
        fm.beginTransaction().add(R.id.frame_layout, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.frame_layout, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.frame_layout,fragment1, "1").commit()

        fab_next.setOnClickListener {
            if(position<3) {
                position++
                when (position) {
                    1 -> {
                        hideFab()
                        fm.beginTransaction().detach(fragment3).commit()
                        fm.beginTransaction().detach(fragment4).commit()
                        fm.beginTransaction().detach(fragment2).commit()
                        fm.beginTransaction().attach(fragment2).commit()
                        fm.beginTransaction().hide(active).show(fragment2).commit()
                        active = fragment2
                        showFab()
                    }
                    2 -> {
                        hideFab()
                        fm.beginTransaction().detach(fragment1).commit()
                        fm.beginTransaction().detach(fragment2).commit()
                        fm.beginTransaction().attach(fragment3).commit()
                        fm.beginTransaction().hide(active).show(fragment3).commit()
                        active = fragment3
                        showFab()
                    }
                    3 -> { hideFab()
                        fm.beginTransaction().detach(fragment3).commit()
                        fm.beginTransaction().detach(fragment4).commit()
                        fm.beginTransaction().attach(fragment4).commit()
                        fm.beginTransaction().hide(active).show(fragment4).commit()
                        active = fragment4
                    }
                }
            }

        }



    }

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
fun showFab(){
    fab_next.visibility = View.VISIBLE
}
    fun hideFab(){
        fab_next.visibility = View.GONE
    }

}
