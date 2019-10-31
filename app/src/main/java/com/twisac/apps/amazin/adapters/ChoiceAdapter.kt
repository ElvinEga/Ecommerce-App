package com.twisac.apps.amazin.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
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
import kotlinx.android.synthetic.main.item_choice.view.*



class ChoiceAdapter(internal var context: Context, private val mChoices: MutableList<Choice>, private var reloadContrib: IReloadContrib)
    : RecyclerView.Adapter<ChoiceAdapter.ViewHolder>() {
    fun clear() {
        mChoices.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var iv_icon: ImageView = v.iv_icon
        var tv_name: TextView = v.tv_name
        var ll_choice: LinearLayout = v.ll_choice
        var rl_layout: RelativeLayout = v.rl_layout
        var cv_choice: CardView = v.cv_choice

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val choice = mChoices[position]
        val rainbow = context.resources.getIntArray(R.array.array_rainbow)
        holder.tv_name.text = choice.name
        val resId = context.resources.getIdentifier(choice.drawable, "drawable", context.packageName)
        holder.iv_icon.setImageResource(resId)
        holder.cv_choice.setCardBackgroundColor(rainbow[position])

        holder.rl_layout.setOnClickListener {
            if (mChoices[position].checked){
                holder.rl_layout.setBackgroundColor(rainbow[17])
                mChoices[position].checked= false
                reloadContrib.reload(-1)
            }else {
                holder.rl_layout.setBackgroundColor(rainbow[18])
                mChoices[position].checked = true
                reloadContrib.reload(+1)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_choice, parent, false)
        return ViewHolder(view)

    }

   override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }
    override fun getItemCount(): Int = mChoices.size

}