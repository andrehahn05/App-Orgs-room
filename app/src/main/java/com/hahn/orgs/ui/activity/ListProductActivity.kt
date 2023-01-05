package com.hahn.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityListProductBinding
import com.hahn.orgs.ui.recyclerView.adapter.ProductListAdapter

class ListProductActivity : AppCompatActivity() {
    
//    private var productId: Long = 0L
//    private var product: Product? = null
    private val adapter = ProductListAdapter(context = this)
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
        confgFab()
    }
    
    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        adapter.toUpdate(products = productDao.findAll())
        
    }
    
    private fun confgFab() {
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
            
            productDao.remove(it)
    
            Toast.makeText(this,"Produto Deletado", Toast.LENGTH_SHORT).show()
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


