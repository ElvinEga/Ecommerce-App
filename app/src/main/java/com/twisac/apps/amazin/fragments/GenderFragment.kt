package com.twisac.apps.amazin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twisac.apps.amazin.QuizActivity
import com.twisac.apps.amazin.R
import com.twisac.apps.amazin.component.SessionManager
import kotlinx.android.synthetic.main.fragment_gender.view.*

/**
 * A simple [Fragment] subclass.
 */
class GenderFragment : androidx.fragment.app.Fragment()

{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_gender, container, false)

        rootView.btn_female.setOnClickListener {
            (activity as QuizActivity).goNext()
            SessionManager(context).createGenderSession("Female")
        }
        rootView.btn_male.setOnClickListener {
            (activity as QuizActivity).goNext()
            SessionManager(context).createGenderSession("Male")
        }
        rootView.cv_female.setOnClickListener {
            (activity as QuizActivity).goNext()
            SessionManager(context).createGenderSession("Female")
        }
        rootView.cv_male.setOnClickListener {
            (activity as QuizActivity).goNext()
            SessionManager(context).createGenderSession("Male")
        }
        return  rootView

    }


}// Required empty public constructor

