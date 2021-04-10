package com.ntikhoa.violapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.model.tempo_term.TempoTerm
import com.ntikhoa.violapp.databinding.ItemTempoTermBinding

class TempoTermAdapter(private val context: Context, private var currentTempo: Int) :
    RecyclerView.Adapter<TempoTermAdapter.TempoTermViewHolder>() {

    private val TEMPO_TERMS = TempoTerm.TEMPO_TERMS

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempoTermViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTempoTermBinding.inflate(inflater, parent, false)
        return TempoTermViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TempoTermViewHolder, position: Int) {
        holder.bind(TEMPO_TERMS[position])
    }

    override fun getItemCount() = TEMPO_TERMS.size

    inner class TempoTermViewHolder(private val binding: ItemTempoTermBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        currentTempo = TEMPO_TERMS[position].getAVGtempo()
                        notifyDataSetChanged()
                        it.onClick(TEMPO_TERMS[position])
                    }
                }
            }
        }

        fun bind(tempoTerm: TempoTerm) {
            binding.apply {
                textViewTempoTerm.text = tempoTerm.term
                val tempoRangeStr = "${tempoTerm.minTempo} - ${tempoTerm.maxTempo}"
                textViewTempoRange.text = tempoRangeStr
                setAllViewColor(R.color.white)

                if (currentTempo >= tempoTerm.minTempo
                    && currentTempo <= tempoTerm.maxTempo
                ) {
                    setAllViewColor(R.color.teal)
                }
            }
        }

        private fun setAllViewColor(colorResId: Int) {
            binding.apply {
                textViewTempoTerm.setTextColor(ContextCompat.getColor(context, colorResId))
                textViewTempoRange.setTextColor(ContextCompat.getColor(context, colorResId))
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(tempoTerm: TempoTerm)
    }
}