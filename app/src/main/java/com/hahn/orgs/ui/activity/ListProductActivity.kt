package com.hahn.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityListProductBinding
import com.hahn.orgs.ui.recyclerView.adapter.ProductListAdapter

class ListProductActivity : AppCompatActivity() {
    
    private val adapter = ProductListAdapter(context = this)
    private val binding by lazy {
        ActivityListProductBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecyclerView()
        confgFab()
    }
    
    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        adapter.update(products = productDao.findAll())
    }
    
    private fun confgFab() {
        val fab = binding.activityListProdFab
        fab.setOnClickListener {
            val intent = Intent(this, FormProductActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun configRecyclerView() {
        val recyclerView = binding.activityListProdRecyclerView
        recyclerView.adapter = adapter
        adapter.handlerClickOnItem = {
            val intent = Intent(this,
                DetailsProductActivity::class.java
            ).apply {
                putExtra(KEY_PRODUCT, it)
            }
            startActivity(intent)
        }
    }
}