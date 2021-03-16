package com.ntikhoa.violapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntikhoa.violapp.databinding.TempoTermItemBinding

class TempoTermAdapter : RecyclerView.Adapter<TempoTermAdapter.TempoTermViewHolder>() {

    private val TEMPO_TERMS = TempoTerm.TEMPO_TERMS

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempoTermViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TempoTermItemBinding.inflate(inflater, parent, false)
        return TempoTermViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TempoTermViewHolder, position: Int) {
        holder.bind(TEMPO_TERMS[position])
    }

    override fun getItemCount() = TEMPO_TERMS.size

    inner class TempoTermViewHolder(val binding: TempoTermItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (onItemClickListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener?.onClick(TEMPO_TERMS[position])
                    }
                }
            }
        }

        fun bind(tempoTerm: TempoTerm) {
            binding.apply {
                textViewTempoTerm.setText(tempoTerm.term)
                val tempoRangeStr = "${tempoTerm.minTempo} - ${tempoTerm.maxTempo}"
                textViewTempoRange.setText(tempoRangeStr)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(tempoTerm: TempoTerm)
    }
}