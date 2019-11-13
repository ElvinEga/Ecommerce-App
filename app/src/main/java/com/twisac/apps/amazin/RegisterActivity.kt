package com.twisac.apps.amazin

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.WindowManager

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.twisac.apps.amazin.component.AlertPopup
import com.twisac.apps.amazin.component.Validator
import com.twisac.apps.amazin.models.request.RegisterRequest
import com.twisac.apps.amazin.models.response.Reply
import com.twisac.apps.amazin.networking.ApiClient
import com.twisac.apps.amazin.networking.ApiInterface
import kotlinx.android.synthetic.main.activity_register.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {


     var alertPopup: AlertPopup = AlertPopup()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        AlertPopup().statusBarTint(this@RegisterActivity)

        btn_register.setOnClickListener { signUp() }
        btn_login.setOnClickListener { finish() }


    }

    private fun signUp() {
        val validator = Validator()
        if (validator.validateInput(editText_phone, input_phone) && validator.validateInput(editText_password, input_password)) {
            if (editText_password.text.toString().equals(editText_confirmpassword.text.toString())) {
                sendUserDetails()
            } else {
                input_password.error = "Password does not match"
            }
        }

    }

    private fun sendUserDetails() {

        val pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Please wait..."
        pDialog.setCancelable(false)
        pDialog.show()

        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val registerRequest = RegisterRequest()
        registerRequest.email = editText_phone.text.toString()
        registerRequest.first_name = editText_firstname.text.toString()
        registerRequest.last_name = editText_lastname.text.toString()
        registerRequest.password = editText_confirmpassword.text.toString()
        registerRequest.role = "user"

        val call = apiService.postRegister(registerRequest)
        call.enqueue(object : Callback<Reply> {
            override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
                val register = response.body()
                if (response.code() == 201) {
                    //if success code
                    pDialog.setTitleText("Success!")
                            .setContentText("Registered Successfully")
                            .setConfirmClickListener(null)
                            .showCancelButton(false)
                            .setConfirmText("OK")
                            .setConfirmClickListener { finish() }
                            .showCancelButton(false)
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)

                } else if(response.code() == 400) {


                    //if failed code
                    pDialog.dismiss()
                    AlertPopup().alertError(this@RegisterActivity, "Error", "Phone Already exists")

                }else{
                    pDialog.dismiss()
                    AlertPopup().alertError(this@RegisterActivity, "Opps", "Opps Something went wrong")

                }

            }

            override fun onFailure(call: Call<Reply>, t: Throwable) {
                pDialog.dismiss()
                alertPopup.alertConnectError(this@RegisterActivity)
            }
        })


    }

/*
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
*/

}
