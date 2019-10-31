package com.twisac.apps.amazin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twisac.apps.amazin.QuizActivity
import com.twisac.apps.amazin.R
import kotlinx.android.synthetic.main.fragment_gender.view.*

/**
 * A simple [Fragment] subclass.
 */
class GenderFragment : Fragment()

{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_gender, container, false)

        rootView.btn_female.setOnClickListener {
            (activity as QuizActivity).showFab()
        }
        rootView.btn_male.setOnClickListener {
            (activity as QuizActivity).showFab()
        }
        return  rootView

    }


}// Required empty public constructor

