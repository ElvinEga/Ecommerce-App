package com.twisac.apps.amazin.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.twisac.apps.amazin.MainActivity
import com.twisac.apps.amazin.QuizActivity
import com.twisac.apps.amazin.R
import com.twisac.apps.amazin.component.AlertPopup
import kotlinx.android.synthetic.main.fragment_measurment.view.*

/**
 * A simple [Fragment] subclass.
 */
class MeasurmentFragment : Fragment()

{
    private var position =0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_measurment, container, false)

        rootView.btn_next.setOnClickListener {
            if(position<3) {
                position++
                when (position) {
                    1 -> {
                        rootView.ll_shirt.visibility=View.GONE
                        rootView.ll_pant.visibility=View.VISIBLE

                    }
                    2 -> {
                        rootView.ll_pant.visibility=View.GONE
                        rootView.ll_shoe.visibility=View.VISIBLE
                        rootView.btn_next.text = "Done"
                    }
                    3 -> {
                  //      AlertPopup().alertSuccess(activity,"Success","Your personal preferences have been shaved")

                        try {
                            val successAlert = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                            successAlert.setTitleText("Profile Updated")
                                    .setContentText("Your personal preferences have been shaved")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener {
                                        val intent = Intent(context, MainActivity::class.java)
                                        // Closing all the Activities
                                        intent .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                                        // Add new Flag to start new Activity
                                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                                        successAlert.dismiss()
                                       startActivity(intent)

                                        activity!!.finish()
                                    }
                                    .show()
                        } catch (ex: WindowManager.BadTokenException) {
                            ex.printStackTrace()
                        }

                    }
                }
            }
        }
        return  rootView

    }


}// Required empty public constructor

