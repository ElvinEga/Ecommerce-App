package com.twisac.apps.amazin.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.WindowManager

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.twisac.apps.amazin.LoginActivity


/**
 * Created by simba on 3/3/17.
 */

class AlertPopup {

    fun alertConnectError(context: Context) {
        try {
            val failedAlert = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            failedAlert.setTitleText("Connectivity Error")
                    .setContentText("Check Your Internet Connection!")
                    .setCancelText("Retry")
                    .setConfirmText("Settings")
                    .showCancelButton(true).setConfirmClickListener {
                        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                        context.startActivity(intent)
                        failedAlert.dismiss()
                    }
                    .setCancelClickListener { failedAlert.dismiss() }.show()
        } catch (ex: WindowManager.BadTokenException) {
            ex.printStackTrace()
        }

    }

    fun alertError(context: Context, title: String, message: String) {
        try {
            val failedAlert = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            failedAlert.setTitleText(title)
                    .setContentText(message)
                    .setConfirmText("OK")
                    .setConfirmClickListener { failedAlert.dismiss() }
                    .show()
        } catch (ex: WindowManager.BadTokenException) {
            ex.printStackTrace()
        }

    }

    fun alertWarning(context: Context, title: String, message: String) {
        try {
            val failedAlert = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
            failedAlert.setTitleText(title)
                    .setContentText(message)
                    .setConfirmText("OK")
                    .setConfirmClickListener { failedAlert.dismiss() }
                    .show()
        } catch (ex: WindowManager.BadTokenException) {
            ex.printStackTrace()
        }

    }


    fun alertSuccess(context: Context, title: String, message: String) {
        val failedAlert = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
        failedAlert.setTitleText(title)
                .setContentText(message)
                .show()
    }

    fun alertTokenExpired(sp: SharedPreferences, context: Context) {
        val tokenAlert = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
        tokenAlert.setTitleText("Session Expired")
                .setContentText("Your login Session has Expired Please Login Again")
                .setConfirmText("OK")
                .setConfirmClickListener {
                    tokenAlert.dismiss()
                    val editor = sp.edit()
                    editor.clear()
                    editor.commit()
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                    //finish();
                }
                .showCancelButton(false)
                .changeAlertType(SweetAlertDialog.WARNING_TYPE)
        tokenAlert.show()


    }

    fun alertLogout(activity: Activity) {
        try {
            val tokenAlert = SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
            tokenAlert.setTitleText("Logout?")
                    .setContentText("Are you sure you want to logout?")
                    .setConfirmText("Logout")
                    .setCancelText("No")
                    .setConfirmClickListener {
                        tokenAlert.dismiss()
                        SessionManager(activity).logoutUser(activity)
                    }
                    .showCancelButton(true).show()
        } catch (ex: WindowManager.BadTokenException) {
            ex.printStackTrace()
        }


    }

    fun statusBarTint(activity: Activity) {

        // get current flag
        // add LIGHT_STATUS_BAR to flag
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.parseColor("#f9f8fb")
                window.navigationBarColor = Color.parseColor("#bdc3c7")
            }
        }
        // get current flag
        // add LIGHT_STATUS_BAR to flag
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.parseColor("#f9f8fb")
                window.navigationBarColor = Color.parseColor("#bdc3c7")

                var flags = activity.window.decorView.systemUiVisibility // get current flag
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR   // add LIGHT_STATUS_BAR to flag
                activity.window.decorView.systemUiVisibility = flags
            }
        }
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                val window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.parseColor("#f9f8fb")
                window.navigationBarColor = Color.parseColor("#f9f8fb")

                var flags = activity.window.decorView.systemUiVisibility // get current flag
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR   // add LIGHT_STATUS_BAR to flag
                activity.window.decorView.systemUiVisibility = flags
            }
        }

    }
}


