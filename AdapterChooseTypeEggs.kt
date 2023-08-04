package com.example.timerforeggs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.timerforeggs.data_classes.EggsInfo
import com.example.timerforeggs.databinding.ForRecViewBinding


class AdapterChooseTypeEggs(private val listener: Clickable, private var listTimer: List<EggsInfo>) :
    RecyclerView.Adapter<AdapterChooseTypeEggs.MyViewHolder>() {

    class MyViewHolder(private val binding: ForRecViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(property: EggsInfo, listener: Clickable) {

            binding.infoForEgg.text = property.info
            binding.buttonTime.text = property.buttonText

            Glide.with(binding.root.context)
                .load(property.image)
                .circleCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.image)

            binding.buttonTime.setOnClickListener {
                listener.onClick(property.time)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ForRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
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

