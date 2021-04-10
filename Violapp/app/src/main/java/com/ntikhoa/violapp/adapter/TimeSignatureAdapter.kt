package com.ntikhoa.violapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.ItemTimeSignatureBinding

class TimeSignatureAdapter(
    private val context: Context,
    var currentTimeSignature: Int,
    private val timeSignatures: IntArray
) :
    RecyclerView.Adapter<TimeSignatureAdapter.TimeSignatureViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSignatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTimeSignatureBinding.inflate(inflater, parent, false)
        return TimeSignatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeSignatureViewHolder, position: Int) {
        holder.bind(timeSignatures[position])
    }

    override fun getItemCount() = timeSignatures.size

    inner class TimeSignatureViewHolder(private val binding: ItemTimeSignatureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnTimeSignature.setOnClickListener {
                if (onItemClickListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION
                        && position != currentTimeSignature - 1
                    ) {
                        onItemClickListener?.onClick(timeSignatures[position])
                    }
                }
            }
        }

        fun bind(timeSignature: Int) {
            binding.btnTimeSignature.apply {
                text = timeSignature.toString()
                when (timeSignature) {
                    currentTimeSignature -> setAllViewColor(R.color.teal, R.color.white)
                    2, 3, 4, 6, 9 -> setAllViewColor(R.color.white, R.color.black)
                    else -> setAllViewColor(R.color.gray, R.color.black)
                }
            }
        }

        private fun setAllViewColor(backgroundColorResId: Int, textColorResId: Int) {
            binding.btnTimeSignature.apply {
                setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        backgroundColorResId
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        context,
                        textColorResId
                    )
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(timeSignature: Int)
    }
}
