package com.hahn.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityListProductBinding
import com.hahn.orgs.extensions.toast
import com.hahn.orgs.ui.recyclerView.adapter.ProductListAdapter
import kotlinx.coroutines.launch

class ListProductActivity : AppCompatActivity() {
    
    private val adapter = ProductListAdapter(this)
    private val binding by lazy {
        ActivityListProductBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        AppDatabase.getInstance(this).productDao()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecyclerView()
        handleFab()
        lifecycleScope.launch {
            refreshScreen()
        }
    }
    
    
    
    private fun refreshScreen() {
        lifecycleScope.launch {
            dao.findAll().collect{ product ->
                adapter.toUpdate(product)
            }
        }
    }
    
    private fun handleFab() {
        val fab = binding.activityListProdFab
        fab.setOnClickListener {
            navigateToForm()
        }
    }
    
    private fun navigateToForm() {
        Intent(this@ListProductActivity, FormProductActivity::class.java)
            .apply {
                startActivity(this)
            }
    }
    
    @SuppressLint("NotifyDataSetChanged")
    private fun configRecyclerView() {
        val recyclerView = binding.activityListProdRecyclerView
        recyclerView.adapter = adapter
        adapter.handleClickOnItem = {
            Intent(this@ListProductActivity, DetailsProductActivity::class.java)
                .apply {
                    putExtra(KEY_PRODUCT_ID, it.id)
                    startActivity(this)
                }
        }
        
        adapter.handleClickOnRemove = {
            lifecycleScope.launch {
                dao.remove(it)
                toast("Produto removido com sucesso!!")
            }
        }
        
        adapter.handleClickOnEdit = {
            Intent(this@ListProductActivity, FormProductActivity::class.java)
                .apply {
                    putExtra(KEY_PRODUCT_ID, it.id)
                    startActivity(this)
                }
        }
        
    }
}


