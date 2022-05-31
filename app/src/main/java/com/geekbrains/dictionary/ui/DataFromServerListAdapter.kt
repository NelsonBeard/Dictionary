package com.geekbrains.dictionary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.dictionary.databinding.TranslationItemBinding
import com.geekbrains.dictionary.domain.entity.DataFromServer

class DataFromServerListAdapter :
    RecyclerView.Adapter<DataFromServerListAdapter.DataFromServerViewHolder>() {

    private var dataFromServer: List<DataFromServer> = emptyList()

    fun setData(translations: List<DataFromServer>) {
        dataFromServer = translations
        notifyDataSetChanged()
    }

    inner class DataFromServerViewHolder(
        private val binding: TranslationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataFromServer) {
            binding.translationHeaderTextView.text = item.text
            binding.translationBodyTextView.text =
                item.meanings?.get(0)?.translation?.translation

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataFromServerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DataFromServerViewHolder(TranslationItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: DataFromServerViewHolder, position: Int) {
        holder.bind(dataFromServer[position])
    }

    override fun getItemCount(): Int {
        return dataFromServer.size
    }


}