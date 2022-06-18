package com.geekbrains.dictionary.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.dictionary.databinding.HistoryItemBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var headers: List<String> = emptyList()

    fun setData(data: List<String>) {
        headers = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HistoryViewHolder(HistoryItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(headers[position])
    }

    override fun getItemCount(): Int {
        return headers.size
    }

    inner class HistoryViewHolder(
        private val binding: HistoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.historyHeaderTextView.text = item
        }
    }
}