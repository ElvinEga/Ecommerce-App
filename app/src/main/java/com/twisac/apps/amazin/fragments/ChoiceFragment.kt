package com.twisac.apps.amazin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twisac.apps.amazin.QuizActivity
import com.twisac.apps.amazin.R
import com.twisac.apps.amazin.adapters.ChoiceAdapter
import com.twisac.apps.amazin.models.Choice
import kotlinx.android.synthetic.main.fragment_choice.view.*
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class ChoiceFragment : Fragment()

{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_choice, container, false)
        var choiceList: MutableList<Choice>
        var gson = Gson()
        val listType = object : TypeToken<List<Choice>>() {}.type
        choiceList = gson.fromJson<MutableList<Choice>>(loadJSONFromAsset("choices.json"), listType)

        val choiceAdapter = ChoiceAdapter(activity!!.applicationContext, choiceList)
        rootView.rv_choice.setHasFixedSize(true)
        rootView.rv_choice.layoutManager = GridLayoutManager(activity, 2)
        val animationController2 = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_slide_from_bottom)
        rootView.rv_choice.layoutAnimation = animationController2

        rootView.rv_choice.adapter = choiceAdapter
        choiceAdapter.notifyDataSetChanged()
        rootView.rv_choice.scheduleLayoutAnimation()
    //    (activity as QuizActivity).showFab()
        return  rootView

    }

    private fun loadJSONFromAsset(fileName:String): String? {
        return try {

            val `is` = activity!!.assets.open(fileName)

            val size = `is`.available()

            val buffer = ByteArray(size)

            `is`.read(buffer)

            `is`.close()

            String(buffer,  charset("UTF-8"))

        } catch (ex: IOException) {ex.printStackTrace(); null
        }

    }
}// Required empty public constructor

