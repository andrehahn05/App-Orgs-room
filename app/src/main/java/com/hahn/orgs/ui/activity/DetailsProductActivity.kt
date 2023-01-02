package com.hahn.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.R
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityProductDetailsBinding
import com.hahn.orgs.extensions.formatPtBr
import com.hahn.orgs.extensions.tryloadimage
import com.hahn.orgs.model.Product


class DetailsProductActivity : AppCompatActivity() {
    
    private var productId: Long = 0L
    private var product: Product? = null
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }
    
    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }
    
    override fun onResume() {
        super.onResume()
        product = productDao.findById(productId)
        product?.let {
            completedFields(it)
        } ?: finish()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_prod_details_remove -> {
                product?.let { productDao.remove(it) }
                finish()
            }
            R.id.menu_prod_details_edit -> {
                Intent(this, FormProductActivity::class.java).apply {
                    putExtra(KEY_PRODUCT_ID, productId)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    
    private fun tryLoadProduct() {
        productId = intent.getLongExtra(KEY_PRODUCT_ID, 0L)
    }
    
    private fun completedFields(loadedProduct: Product) {
        with(binding) {
            activityDetailsProductImage.tryloadimage(loadedProduct.image)
            activityDetailsProductName.text = loadedProduct.name
            activityDetailsProductDescription.text = loadedProduct.description
            activityDetailsProductValue.text = loadedProduct.price.formatPtBr()
        }
        
    }
}