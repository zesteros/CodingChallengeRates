package com.angelo.codingchallenge.presentation.symbols

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelo.codingchallenge.R
import com.angelo.codingchallenge.databinding.ActivitySymbolsBinding
import com.angelo.codingchallenge.presentation.CurrencyAdapter
import com.angelo.codingchallenge.presentation.core.ErrorDialogFragment
import com.angelo.codingchallenge.presentation.di.Injector
import com.angelo.codingchallenge.presentation.rates.RatesActivity
import javax.inject.Inject


class SymbolsActivity : AppCompatActivity(), CurrencyAdapter.CurrencyHolderListener<String>,
    SearchView.OnQueryTextListener {

    private lateinit var binding: ActivitySymbolsBinding
    private val TAG: String =
        SymbolsActivity::class.java.simpleName

    @Inject
    lateinit var factory: SymbolsViewModelFactory
    private lateinit var symbolsViewModel: SymbolsViewModel
    private lateinit var adapter: CurrencyAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_symbols)
        (application as Injector).createSymbolsSubComponent().inject(this)
        symbolsViewModel = ViewModelProvider(this, factory).get(SymbolsViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CurrencyAdapter()
        adapter.setHolderListener(this)
        binding.recyclerView.adapter = adapter
        displaySupportedSymbols()
    }

    private fun displaySupportedSymbols() {
        val liveData = symbolsViewModel.getSymbol()
        liveData.observe(this, {
            it?.let {
                if (it.success) {
                    adapter.setList(it.symbols.toSortedMap())
                    symbolsViewModel.setSymbolsList(it.symbols.keys)
                    adapter.notifyDataSetChanged()
                } else
                    ErrorDialogFragment(it.error?.code, it.error?.type).show(
                        supportFragmentManager,
                        ErrorDialogFragment.TAG
                    )
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(item: Pair<String, String>) {
        val intent = Intent(this, RatesActivity::class.java)
        intent.putExtra("base", item.first)
        intent.putExtra("symbols", symbolsViewModel.getSymbolsList())
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        symbolsViewModel.searchInSymbols(query).observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return onQueryTextSubmit(newText)
    }
}