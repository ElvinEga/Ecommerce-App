package com.twisac.apps.amazin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.content.Intent
import android.graphics.Color
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.twisac.apps.amazin.component.AlertPopup
import com.twisac.apps.amazin.component.SessionManager
import com.twisac.apps.amazin.models.request.LoginRequest
import com.twisac.apps.amazin.models.response.Login
import com.twisac.apps.amazin.networking.ApiClient
import com.twisac.apps.amazin.networking.ApiInterface
import kotlinx.android.synthetic.main.activity_login.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(){
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_register.setOnClickListener { val intent = Intent(this.applicationContext, RegisterActivity::class.java)
            startActivity(intent) }
        // Set up the login form.
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        email_sign_in_button.setOnClickListener { attemptLogin() }

        //Animation

       tv_logo.translationX=400.toFloat()
        tv_logo.alpha=0.toFloat()

        tv_motto.translationX=400.toFloat()
        tv_motto.alpha=0.toFloat()

        btn_sign_up.translationX=400.toFloat()
        btn_sign_up.alpha=0.toFloat()

        btn_log_in.translationX=400.toFloat()
        btn_log_in.alpha=0.toFloat()

        iv_sign_up_logo.translationX=400.toFloat()
        iv_sign_up_logo.alpha=0.toFloat()

        email_sign_in_button.translationX=400.toFloat()
        email_sign_in_button.alpha=0.toFloat()

        tv_register.translationX=400.toFloat()
        tv_register.alpha=0.toFloat()

        tv_logo.animate().translationX(0.toFloat()).alpha(1.toFloat()).setDuration(800).setStartDelay(500).start()
        tv_motto.animate().translationX(0.toFloat()).alpha(1.toFloat()).setDuration(800).setStartDelay(700).start()
        btn_sign_up.animate().translationX(0.toFloat()).alpha(1.toFloat()).setDuration(800).setStartDelay(900).start()
        btn_log_in.animate().translationX(0.toFloat()).alpha(1.toFloat()).setDuration(800).setStartDelay(900).start()


        btn_log_in.setOnClickListener {
            var fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop)
            var frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom)

            rl_start_page.visibility = View.GONE
            activity_sign_up.startAnimation(frombottom)

            password.startAnimation(fromtop)
            et_username.startAnimation(fromtop)
            iv_sign_up_logo.animate().translationX(0.toFloat()).alpha(1.toFloat()).setDuration(800).setStartDelay(500).start()
            email_sign_in_button.animate().translationX(0.toFloat()).alpha(1.toFloat()).setDuration(800).setStartDelay(700).start()
            tv_register.animate().translationX(0.toFloat()).alpha(1.toFloat()).setDuration(800).setStartDelay(900).start()
            //white top
     AlertPopup().statusBarTint(this@LoginActivity)

        }
        btn_sign_up.setOnClickListener { val intent = Intent(this.applicationContext, RegisterActivity::class.java)
            startActivity(intent)
            AlertPopup().statusBarTint(this@LoginActivity)
        }
    }





    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {

        // Reset errors.
        et_username.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val usernameStr = et_username.text.toString().trim()
        val passwordStr = password.text.toString().trim()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = "Password is Invalid"
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(usernameStr)) {
            et_username.error = "Username is Required"
            focusView = et_username
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            sendLogin(usernameStr,passwordStr)


        }
    }


    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }



    private fun alertLoginError() {
        AlertPopup().alertError(this, "Login Error", "Incorrect Username or Password")
    }

    private fun alertConnectError() {
        AlertPopup().alertConnectError(this)
    }

    private fun sendLogin(usernameStr:String,passwordStr: String) {

        //Loading Progress Dialog
        val pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Logging in"
        pDialog.setCancelable(false)
        pDialog.show()

        //Retrofit Interface for Endpoints
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        //Setup Post Request Object
        val loginRequest = LoginRequest()
        loginRequest.username=usernameStr
        loginRequest.password=passwordStr

        //Retrofit Api call
        val call = apiService.postLogin(loginRequest)
        call.enqueue(object : Callback<Login> {
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                //Response code status check
                if (response.code() == 200) {
                    pDialog.setTitleText("Success!")
                            .setContentText("You are now logged in!")
                            .setConfirmText("OK")
                            .setConfirmClickListener(null)
                            .showCancelButton(false)
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)

                    SessionManager(this@LoginActivity).createLoginSession(response.body()!!.id, response.body()!!.name, usernameStr, passwordStr, response.body()!!.role, "Token " + response.body()!!.token)
                    //Check role of User to login
                    pDialog.dismiss()
                    val mainActivity = Intent(applicationContext, QuizActivity::class.java)
                    startActivity(mainActivity)
                    finish()

                } else if (response.code() == 403) {
                    pDialog.dismiss()
                    AlertPopup().alertError(this@LoginActivity, "Error", "Incorrect Username or Password")

                } else {
                    pDialog.dismiss()
                    alertLoginError()


                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                //Network error or Connectivity
                pDialog.dismiss()
                alertConnectError()
            }
        })


    }


/*
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
*/

}
