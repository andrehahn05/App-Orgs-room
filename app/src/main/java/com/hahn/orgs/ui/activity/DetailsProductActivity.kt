package com.hahn.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.R
import com.hahn.orgs.databinding.ActivityProductDetailsBinding
import com.hahn.orgs.extensions.formatPtBr
import com.hahn.orgs.extensions.tryloadimage
import com.hahn.orgs.model.Product


class DetailsProductActivity : AppCompatActivity() {
    
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_details_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_prod_details_remove -> Log.i("remover","onOptionsItemSelected: remover")
            R.id.menu_prod_details_edit -> Log.i("editar","onOptionsItemSelected: editar")
        }

        return super.onOptionsItemSelected(item)
    }
    
    
    private fun tryLoadProduct() {
        @Suppress("DEPRECATION")
        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let { loadedProduct ->
            completedFields(loadedProduct)
        } ?: finish()
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