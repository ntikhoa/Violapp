package com.ntikhoa.violapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.ItemScaleBinding
import com.ntikhoa.violapp.model.Scale.Scale
import com.ntikhoa.violapp.model.Scale.ScaleList

class ScaleAdapter(var currentScale: Scale) : RecyclerView.Adapter<ScaleAdapter.ScaleViewHolder>() {

    private val scales = ScaleList.scales

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScaleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemScaleBinding.inflate(inflater, parent, false)
        return ScaleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScaleViewHolder, position: Int) {
        val scale = scales[position]
        holder.bind(scale)
    }


    override fun getItemCount() = scales.size

    inner class ScaleViewHolder(val binding: ItemScaleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val scale = scales[position]
                        currentScale = scale
                        notifyDataSetChanged()
                        it.onClick(scale)
                    }
                }
            }
        }

        fun bind(scale: Scale) {
            binding.apply {
                imageScale.setImageResource(scale.imageResId)
                textViewScaleName.text = scale.name
                textViewScaleName.setBackgroundResource(R.color.white)
                if (scale.name == currentScale.name) {
                    textViewScaleName.setBackgroundResource(R.color.teal)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(scale: Scale)
    }
}