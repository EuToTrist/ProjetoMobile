package com.avanade.currencyconverter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CardAdapter(private val dataList: List<String>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view_nome_doenca)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_layout, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = dataList[position]
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}