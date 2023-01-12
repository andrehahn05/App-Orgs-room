package com.hahn.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.hahn.orgs.R
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityListProductBinding
import com.hahn.orgs.extensions.toast
import com.hahn.orgs.ui.recyclerView.adapter.ProductListAdapter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ListProductActivity : UserBaseActivity() {
    
    private val adapter = ProductListAdapter(this)
    private val binding by lazy {
        ActivityListProductBinding.inflate(layoutInflater)
    }
    private val productDao by lazy {
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
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_product, menu)
        return super.onCreateOptionsMenu(menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuSetup(item)
        return super.onOptionsItemSelected(item)
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
    
    private suspend fun refreshScreen() {
        user.filterNotNull().collect() { user ->
            productDao.find(user.id).collect {
                adapter.toUpdate(it)
            }
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
                productDao.remove(it)
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
    
    private fun menuSetup(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_logout -> {
                lifecycleScope.launch {
                    logoutUser()
                }
            }
            R.id.menu_list_product_order_nameAsc -> {
                lifecycleScope.launch {
                    productDao.orderNameAsc().collect() {
                        adapter.toUpdate(it)
                    }
                }
            }
            R.id.menu_list_product_order_nameDesc -> {
                lifecycleScope.launch {
                    productDao.orderNameDesc().collect() {
                        adapter.toUpdate(it)
                    }
                }
            }
            R.id.menu_list_product_order_valueAsc -> {
                lifecycleScope.launch {
                    productDao.orderValueAsc().collect() {
                        adapter.toUpdate(it)
                    }
                }
            }
            R.id.menu_list_product_order_valueDesc -> {
                lifecycleScope.launch {
                    productDao.orderValueDesc().collect() {
                        adapter.toUpdate(it)
                    }
                }
            }
            R.id.menu_list_product_order_noOrder -> {
                lifecycleScope.launch {
                    refreshScreen()
                }
            }
        }
    }
}


