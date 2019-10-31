package com.twisac.apps.amazin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twisac.apps.amazin.QuizActivity
import com.twisac.apps.amazin.R
import com.twisac.apps.amazin.adapters.QuizAdapter
import com.twisac.apps.amazin.component.AlertPopup
import com.twisac.apps.amazin.interfaces.IReloadContrib
import com.twisac.apps.amazin.models.Choice
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class QuizFragment : Fragment(), IReloadContrib

{
    private var countCoice = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_quiz, container, false)
        var choiceList: MutableList<Choice>
        var gson = Gson()
        val listType = object : TypeToken<List<Choice>>() {}.type
        choiceList = gson.fromJson<MutableList<Choice>>(loadJSONFromAsset("quiz.json"), listType)

        val quizAdapter = QuizAdapter(activity!!.applicationContext, choiceList,this)
        rootView.rv_quiz.setHasFixedSize(true)
        rootView.rv_quiz.layoutManager =  ( LinearLayoutManager(activity!!.applicationContext))
        val animationController2 = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_slide_from_bottom)
        rootView.rv_quiz.layoutAnimation = animationController2

        rootView.rv_quiz.adapter = quizAdapter
        quizAdapter.notifyDataSetChanged()
        rootView.rv_quiz.scheduleLayoutAnimation()
      //  (activity as QuizActivity).showFab()

        rootView.fab_done.setOnClickListener {
            if(countCoice!=0){
                (activity as QuizActivity).showFab()
                (activity as QuizActivity).goNext()
            }else
            {
                AlertPopup().alertError(activity!!,"No Selection","Please select 1 or more choices")

            }


        }
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

    override fun reload(count: Int) {
        countCoice += count
    }
}// Required empty public constructor

