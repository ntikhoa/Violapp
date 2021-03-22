package com.ntikhoa.violapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ntikhoa.violapp.databinding.TimeSignatureItemBinding

class TimeSignatureAdapter(
    private val context: Context,
    private val currentTimeSignature: Int,
    private val timeSignatures: IntArray
) :
    RecyclerView.Adapter<TimeSignatureAdapter.TimeSignatureViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSignatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TimeSignatureItemBinding.inflate(inflater, parent, false)
        return TimeSignatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeSignatureViewHolder, position: Int) {
        holder.bind(timeSignatures[position])
    }

    override fun getItemCount() = timeSignatures.size

    inner class TimeSignatureViewHolder(private val binding: TimeSignatureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnTimeSignature.setOnClickListener {
                if (onItemClickListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener?.onClick(timeSignatures[position])
                    }
                }
            }
        }

        fun bind(timeSignature: Int) {
            binding.btnTimeSignature.apply {
                text = timeSignature.toString()
                when (timeSignature) {
                    currentTimeSignature -> setViewCurrentTimeSignature()

                    2, 3, 4, 6, 9 -> setViewChoosableTimeSignature()
                }
            }
        }

        private fun setViewCurrentTimeSignature() {
            binding.btnTimeSignature.apply {
                setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.orange
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
            }
        }

        private fun setViewChoosableTimeSignature() {
            binding.btnTimeSignature.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
    }

    interface OnItemClickListener {
        fun onClick(timeSignature: Int)
    }
}
