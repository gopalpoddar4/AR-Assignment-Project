package com.gopalpoddar4.arappproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: List<RCVModel>, private val onClick:(Int)->Unit) : RecyclerView.Adapter<AdapterClass.Viewholder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_layout, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(
        holder: Viewholder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            onClick(dataList[position].id)
        }
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

   inner class Viewholder(view: View): RecyclerView.ViewHolder(view){
        val drillText = view.findViewById<TextView>(R.id.drillTxt)

       fun bind(rcvModel: RCVModel){
            drillText.text = rcvModel.drill
       }
    }
}