package com.angelo.codingchallenge.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.angelo.codingchallenge.R
import com.angelo.codingchallenge.databinding.ListItemBinding
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap

class CurrencyAdapter<V> : RecyclerView.Adapter<CurrencyViewHolder<V>>() {

    private var currencyItems = HashMap<String, V>()
    private lateinit var listener: CurrencyHolderListener<V>

    fun setList(symbols: Map<String, V>) {
        this.currencyItems = HashMap(symbols)
    }

    fun setHolderListener(listener: CurrencyHolderListener<V>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder<V> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder<V>, position: Int) {
        val list = currencyItems.toList()
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int = currencyItems.size

    interface CurrencyHolderListener<V> {
        fun onItemClicked(item: Pair<String, V>)
    }
}

class CurrencyViewHolder<V>(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(symbol: Pair<String, V>, listener: CurrencyAdapter.CurrencyHolderListener<V>?) {
        listener?.let {
            binding.cardView.setOnClickListener {
                listener.onItemClicked(symbol)
            }
        }
        binding.titleTextView.text = symbol.first
        when (symbol.second) {
            is String -> binding.descriptionTextView.text = symbol.second as String
            is Double -> {
                val format: NumberFormat = NumberFormat.getCurrencyInstance()
                format.currency = Currency.getInstance(symbol.first)
                format.isGroupingUsed = false
                binding.descriptionTextView.text = format.format(symbol.second as Double)
            }
        }
    }
}