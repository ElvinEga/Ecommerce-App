package com.twisac.apps.amazin.adapters

import android.app.Activity
import android.content.Context
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.twisac.apps.amazin.QuizActivity
import com.twisac.apps.amazin.R
import com.twisac.apps.amazin.interfaces.IReloadContrib
import com.twisac.apps.amazin.models.Choice
import kotlinx.android.synthetic.main.item_quiz.view.*


class QuizAdapter(internal var context: Context, private val mChoices: MutableList<Choice>,private var reloadContrib: IReloadContrib)
    : androidx.recyclerview.widget.RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    fun clear() {
        mChoices.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var iv_icon: ImageView = v.iv_icon
        var tv_name: TextView = v.tv_name
        var iv_selected: ImageView = v.iv_selected
        var rl_layout: RelativeLayout = v.rl_layout

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val choice = mChoices[position]
        holder.tv_name.text = choice.name
        val resId = context.resources.getIdentifier(choice.drawable, "drawable", context.packageName)
        holder.iv_icon.setImageResource(resId)

        holder.rl_layout.setOnClickListener {
            if (mChoices[position].checked){
                holder.iv_selected.visibility= View.INVISIBLE
                mChoices[position].checked= false
                reloadContrib.reload(-1)
            }else {

                holder.iv_selected.visibility= View.VISIBLE
                mChoices[position].checked = true
                reloadContrib.reload(+1)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_quiz, parent, false)
        return ViewHolder(view)

    }

   override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }
    override fun getItemCount(): Int = mChoices.size

}