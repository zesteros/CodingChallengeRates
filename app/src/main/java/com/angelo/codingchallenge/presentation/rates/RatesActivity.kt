package com.angelo.codingchallenge.presentation.rates

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelo.codingchallenge.R
import com.angelo.codingchallenge.databinding.ActivityRatesBinding
import com.angelo.codingchallenge.presentation.CurrencyAdapter
import com.angelo.codingchallenge.presentation.core.ErrorDialogFragment
import com.angelo.codingchallenge.presentation.di.Injector
import com.google.android.material.internal.TextWatcherAdapter
import javax.inject.Inject

class RatesActivity : AppCompatActivity(), CurrencyAdapter.CurrencyHolderListener<Double> {
    private lateinit var binding: ActivityRatesBinding
    private val TAG: String =
        RatesActivity::class.java.simpleName

    @Inject
    lateinit var factory: RatesViewModelFactory
    private lateinit var ratesViewModel: RatesViewModel
    private lateinit var adapter: CurrencyAdapter<Double>
    private lateinit var base: String
    private lateinit var symbols: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rates)
        (application as Injector).createRatesSubComponent().inject(this)
        ratesViewModel = ViewModelProvider(this, factory).get(RatesViewModel::class.java)

        val extras = intent.extras
        base = extras?.getString("base", "EUR").toString()
        symbols = extras?.getString("symbols", "").toString()

        getRates()

        binding.editText.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handleInput(s)
            }
        })

    }

    private fun handleInput(s: CharSequence) {
        if (s.isNotBlank()) {
            binding.recyclerView.visibility = VISIBLE
            ratesViewModel.calculateRates(s.toString())
            ratesViewModel.ratesLiveData.observe(this, {
                updateRates(it)
            })
        } else
            binding.recyclerView.visibility = GONE
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CurrencyAdapter()
        adapter.setHolderListener(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getRates() {
        initRecyclerView()
        val liveData = ratesViewModel.getRates(base, symbols)
        liveData.observe(this, {
            it?.let {
                if (!it.success)
                    ErrorDialogFragment(it.error?.code, it.error?.type).show(
                        supportFragmentManager,
                        ErrorDialogFragment.TAG
                    )
            }
        })
    }

    fun updateRates(rates: Map<String, Double>) {
        adapter.setList(rates)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClicked(item: Pair<String, Double>) {
        TODO("Not yet implemented")
    }
}