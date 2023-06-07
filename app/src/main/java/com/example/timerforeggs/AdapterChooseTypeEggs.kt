package com.example.timerforeggs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class AdapterChooseTypeEggs(private val listener: Clickable, private var listTimer: List<EggsInfo>) :
    RecyclerView.Adapter<AdapterChooseTypeEggs.MyViewHolder>() {

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: EggsInfo, listener: Clickable) {

            val imageView = view.findViewById<ImageView>(R.id.image)
            val infoEgg = view.findViewById<TextView>(R.id.info_for_egg)
            val button = view.findViewById<Button>(R.id.button_time)

            infoEgg.text = property.info
            button.text = property.buttonText

            Glide.with(view.context)
                .load(property.image)
                .circleCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView)

            button.setOnClickListener {
                listener.onClick(property.time)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.for_rec_view, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listTimer.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listTimer[position], listener)
    }
}

interface Clickable { // интерфейс для слушателя нажатий
    fun onClick(time: Int)
}

